package de.vontrostorff.domain.service;

import de.vontrostorff.domain.model.Location;
import de.vontrostorff.domain.ports.incoming.LocationCommands;
import de.vontrostorff.domain.ports.outgoing.LocationRepo;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class LocationService implements LocationCommands {
    private final LocationRepo locationRepo;
    public double getMultiplier(long locationId){
        return locationRepo.findById(locationId).getMultiplier();
    }

    @Override
    public List<Location> searchByPLZ(int plz){
        return locationRepo.findByPLZ(plz);
    }

    @Override
    public void createLocations(List<Location> locations) {
        locationRepo.persist(locations);
    }

    @Override
    public boolean isLocationDataEmpty() {
        return locationRepo.count() == 0;
    }
}
