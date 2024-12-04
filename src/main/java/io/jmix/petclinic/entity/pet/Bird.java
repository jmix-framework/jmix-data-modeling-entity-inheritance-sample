package io.jmix.petclinic.entity.pet;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

// tag::class[]
@JmixEntity
@Table(name = "PETCLINIC_BIRD")
@Entity(name = "petclinic_Bird")
@PrimaryKeyJoinColumn(name = "ID")
public class Bird extends Pet {
    @Column(name = "WINGSPAN", nullable = false)
    @NotNull
    private Double wingspan;

    public Double getWingspan() {
        return wingspan;
    }

    public void setWingspan(Double wingspan) {
        this.wingspan = wingspan;
    }
}
// end::class[]