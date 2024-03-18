package de.vontrostorff.infrastructure.repository;

import de.vontrostorff.domain.model.Kilometers;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KilometersRepo implements de.vontrostorff.domain.ports.outgoing.KilometersRepo {

    @Override
    public Kilometers findForKilometers(int kilometers) {
        return find("(yearlyKilometersDrivenLower <= ?1 or yearlyKilometersDrivenLower is null) and (yearlyKilometersDrivenUpper >= ?1 or yearlyKilometersDrivenUpper is null)", kilometers)
                .firstResultOptional()
                .orElseThrow();
    }
}