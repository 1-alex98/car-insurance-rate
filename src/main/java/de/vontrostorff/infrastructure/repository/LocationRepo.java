package de.vontrostorff.infrastructure.repository;

import de.vontrostorff.domain.model.Location;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LocationRepo implements de.vontrostorff.domain.ports.outgoing.LocationRepo {

    @Override
    public List<Location> findByPLZ(int plz) {
        return list("plz", plz);
    }
}