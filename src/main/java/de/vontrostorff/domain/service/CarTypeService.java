package de.vontrostorff.domain.service;

import de.vontrostorff.domain.model.CarType;
import de.vontrostorff.domain.ports.incoming.CarTypeCommands;
import de.vontrostorff.domain.ports.outgoing.CarTypeRepo;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class CarTypeService implements CarTypeCommands {
    private final CarTypeRepo carTypeRepo;

    public double getMultiplier(long carTypeId){
        return carTypeRepo.findById(carTypeId).getMultiplier();
    }

    public List<CarType> getAllCarTypes(){
        return carTypeRepo.listAll();
    }
}
