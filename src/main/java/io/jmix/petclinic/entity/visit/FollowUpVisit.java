package io.jmix.petclinic.entity.visit;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

import java.time.LocalDate;

@JmixEntity
@Entity(name = "petclinic_FollowUpVisit")
@DiscriminatorValue("FOLLOW_UP_VISIT")
public class FollowUpVisit extends Visit {
    @Column(name = "NOTES")
    @Lob
    private String notes;

    @Column(name = "SCHEDULED_DATE")
    private LocalDate scheduledDate;

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}