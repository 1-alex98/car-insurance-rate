package de.vontrostorff.domain.ports.incoming;

import de.vontrostorff.domain.model.Location;

import java.util.List;

public interface LocationCommands {
    List<Location> searchByPLZ(int plz);

    void createLocations(List<Location> locations);
}
