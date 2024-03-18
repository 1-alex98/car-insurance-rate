package de.vontrostorff.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Multiplier extends PanacheEntity {
    @Column(nullable = false)
    @JsonIgnore
    double multiplier;
}
