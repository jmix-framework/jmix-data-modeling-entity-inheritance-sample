package io.jmix.petclinic.entity.visit;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@JmixEntity
@Entity(name = "petclinic_EmergencyVisit")
@DiscriminatorValue("EMERGENCY_VISIT")
public class EmergencyVisit extends Visit {
    @Column(name = "EMERGENCY_TYPE")
    private String emergencyType;

    @Column(name = "LIFE_THREATENING")
    private Boolean lifeThreatening;

    public Boolean getLifeThreatening() {
        return lifeThreatening;
    }

    public void setLifeThreatening(Boolean lifeThreatening) {
        this.lifeThreatening = lifeThreatening;
    }

    public EmergencyType getEmergencyType() {
        return emergencyType == null ? null : EmergencyType.fromId(emergencyType);
    }

    public void setEmergencyType(EmergencyType emergencyType) {
        this.emergencyType = emergencyType == null ? null : emergencyType.getId();
    }
}