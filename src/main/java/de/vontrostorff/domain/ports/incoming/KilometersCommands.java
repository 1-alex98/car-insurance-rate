package de.vontrostorff.domain.ports.incoming;

import de.vontrostorff.domain.model.Kilometers;
import jakarta.transaction.Transactional;

public interface KilometersCommands {
    @Transactional
    void createOrUpdateKilometers(Kilometers kilometers);
}
