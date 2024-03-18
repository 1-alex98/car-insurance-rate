package de.vontrostorff.domain.ports.incoming;

import de.vontrostorff.domain.model.CarType;

import java.util.List;

public interface CarTypeCommands {
    List<CarType> getAllCarTypes();
}
