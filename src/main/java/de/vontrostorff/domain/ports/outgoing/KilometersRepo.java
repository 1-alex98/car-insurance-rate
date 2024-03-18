package de.vontrostorff.domain.ports.outgoing;

import de.vontrostorff.domain.model.Kilometers;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface KilometersRepo extends PanacheRepository<Kilometers> {
    Kilometers findForKilometers(int kilometers);
}
