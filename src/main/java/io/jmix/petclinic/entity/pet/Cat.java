package io.jmix.petclinic.entity.pet;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "PETCLINIC_CAT")
@Entity(name = "petclinic_Cat")
@PrimaryKeyJoinColumn(name = "ID")
public class Cat extends Pet {
    @Column(name = "CLAW_LENGTH")
    private Integer clawLength;

    @Column(name = "LITTER_BOX_TRAINED", nullable = false)
    @NotNull
    private Boolean litterBoxTrained = false;

    public Boolean getLitterBoxTrained() {
        return litterBoxTrained;
    }

    public void setLitterBoxTrained(Boolean litterBoxTrained) {
        this.litterBoxTrained = litterBoxTrained;
    }

    public Integer getClawLength() {
        return clawLength;
    }

    public void setClawLength(Integer clawLength) {
        this.clawLength = clawLength;
    }
}