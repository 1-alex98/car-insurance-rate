package de.vontrostorff.domain.ports.outgoing;

import de.vontrostorff.domain.model.Location;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public interface LocationRepo extends PanacheRepository<Location> {
    List<Location> findByPLZ(int plz);
}
