package de.vontrostorff.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class CarType extends Multiplier {
    @Column(nullable = false)
    String name;
    @Column
    String brand;
}