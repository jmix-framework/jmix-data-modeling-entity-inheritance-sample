package io.jmix.petclinic.entity.visit;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;


// tag::start-class[]
@JmixEntity
@Entity(name = "petclinic_RegularCheckup")
@DiscriminatorValue("REGULAR_CHECKUP")
public class RegularCheckup extends Visit {

    // end::start-class[]

    // tag::attributes[]
    @Column(name = "NEXT_APPOINTMENT_DATE")
    private LocalDateTime nextAppointmentDate;

    public LocalDateTime getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public void setNextAppointmentDate(LocalDateTime nextAppointmentDate) {
        this.nextAppointmentDate = nextAppointmentDate;
    }
    // end::attributes[]

    // tag::init-visit-type[]
    @PostConstruct
    public void initVisitType() {
        setType(VisitType.REGULAR_CHECKUP);
    }
    // end::init-visit-type[]
// tag::end-class[]
}
// end::end-class[]