package de.vontrostorff.domain.service;

import de.vontrostorff.domain.model.Kilometers;
import de.vontrostorff.domain.ports.incoming.KilometersCommands;
import de.vontrostorff.domain.ports.outgoing.KilometersRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class KilometersService implements KilometersCommands {
    private final KilometersRepo kilometersRepo;

    public double getMultiplier(int kilometers){
        return kilometersRepo.findForKilometers(kilometers).getMultiplier();
    }

    @Transactional
    public void createOrUpdateKilometers(Kilometers kilometers) {
        // Fetch all Kilometers entities from the database
        List<Kilometers> allKilometers = kilometersRepo.listAll();

        allKilometers.stream()
                .filter(k -> rangesOverlap(kilometers, k))
                .findAny()
                .ifPresent(k -> {
                    throw new ConstraintViolationException("Ranges overlap with an existing entry", null);
                });

        // Persist the new Kilometers entity or merge the updated one
        kilometersRepo.persist(kilometers);
    }

    private boolean rangesOverlap(Kilometers k1, Kilometers k2) {
        // Check if the ranges overlap
        return (k1.getYearlyKilometersDrivenLower() == null || k2.getYearlyKilometersDrivenUpper() == null || k1.getYearlyKilometersDrivenLower() < k2.getYearlyKilometersDrivenUpper())
                && (k1.getYearlyKilometersDrivenUpper() == null || k2.getYearlyKilometersDrivenLower() == null || k1.getYearlyKilometersDrivenUpper() > k2.getYearlyKilometersDrivenLower());
    }
}
