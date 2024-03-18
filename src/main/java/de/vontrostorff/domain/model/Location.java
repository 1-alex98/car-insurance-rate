package de.vontrostorff.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Location extends Multiplier {
    @Column
    String region1;
    @Column
    String region3;
    @Column
    String region4;
    @Column(nullable = false)
    int plz;
    @Column
    String location;
}