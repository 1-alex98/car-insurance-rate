package de.vontrostorff.domain.ports.outgoing;

import de.vontrostorff.domain.model.CarType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface CarTypeRepo extends PanacheRepository<CarType> {
}
