package de.vontrostorff.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Kilometers extends Multiplier {
    @Column
    Integer yearlyKilometersDrivenLower;
    @Column
    Integer yearlyKilometersDrivenUpper;
}