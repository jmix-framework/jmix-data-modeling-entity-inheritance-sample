package io.jmix.petclinic.entity.visit;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@JmixEntity
@Entity(name = "petclinic_RegularCheckup")
@DiscriminatorValue("REGULAR_CHECKUP")
public class RegularCheckup extends Visit {

    @Column(name = "NEXT_APPOINTMENT_DATE")
    private LocalDateTime nextAppointmentDate;

    public LocalDateTime getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public void setNextAppointmentDate(LocalDateTime nextAppointmentDate) {
        this.nextAppointmentDate = nextAppointmentDate;
    }
}