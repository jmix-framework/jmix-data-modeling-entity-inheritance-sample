// tag::imports[]
package io.jmix.petclinic.entity.visit;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.InheritanceType;
// end::imports[]

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.petclinic.entity.NamedEntity;
import io.jmix.petclinic.entity.User;
import io.jmix.petclinic.entity.pet.Pet;

import jakarta.persistence.Index;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Version;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static io.jmix.petclinic.entity.visit.VisitTreatmentStatus.DONE;
import static io.jmix.petclinic.entity.visit.VisitTreatmentStatus.IN_PROGRESS;

// tag::start-class[]


@JmixEntity
@Table(name = "PETCLINIC_VISIT", indexes = {
        @Index(name = "IDX_PETCLINIC_VISIT_ASSIGNED_NURSE", columnList = "ASSIGNED_NURSE_ID"),
        @Index(name = "IDX_PETCLINIC_VISIT_PET", columnList = "PET_ID")
})
@Entity(name = "petclinic_Visit")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("VISIT")
public class Visit {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "PET_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Pet pet;

    @Column(name = "TYPE_", nullable = false)
    @NotNull
    private String type;

    @Column(name = "VISIT_START")
    private LocalDateTime visitStart;

    @Column(name = "VISIT_END")
    private LocalDateTime visitEnd;

    @JoinColumn(name = "ASSIGNED_NURSE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedNurse;

    @InstanceName
    @Column(name = "DESCRIPTION", length = 4000)
    private String description;

    @Column(name = "TREATMENT_STATUS")
    private String treatmentStatus;

    // end::start-class[]

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private OffsetDateTime lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    @DependsOnProperties({"type"})
    @JmixProperty
    public String getTypeTextColor() {
        return Optional.ofNullable(getType())
                .map(VisitType::getTextColor)
                .orElse("");
    }

    @DependsOnProperties({"type"})
    @JmixProperty
    public String getTypeBorderColor() {
        return Optional.ofNullable(getType())
                .map(VisitType::getBorderColor)
                .orElse("");
    }

    @DependsOnProperties({"type"})
    @JmixProperty
    public String getTypeBackgroundColor() {
        return Optional.ofNullable(getType())
                .map(VisitType::getBackgroundColor)
                .orElse("");
    }


    @JmixProperty
    @DependsOnProperties({"type"})
    public String getTypeStyle() {
        return Optional.ofNullable(getType())
                .map(VisitType::getStyleName)
                .orElse("");
    }

    @JmixProperty
    @DependsOnProperties({"pet"})
    public String getPetName() {
        return Optional.ofNullable(getPet())
                .map(NamedEntity::getName)
                .orElse("");
    }

    public VisitTreatmentStatus getTreatmentStatus() {
        return treatmentStatus == null ? null : VisitTreatmentStatus.fromId(treatmentStatus);
    }

    public void setTreatmentStatus(VisitTreatmentStatus treatmentStatus) {
        this.treatmentStatus = treatmentStatus == null ? null : treatmentStatus.getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(LocalDateTime visitEnd) {
        this.visitEnd = visitEnd;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(LocalDateTime visitStart) {
        this.visitStart = visitStart;
    }

    public VisitType getType() {
        return type == null ? null : VisitType.fromId(type);
    }

    public void setType(VisitType type) {
        this.type = type == null ? null : type.getId();
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getAssignedNurse() {
        return assignedNurse;
    }

    public void setAssignedNurse(User assignedNurse) {
        this.assignedNurse = assignedNurse;
    }

    public OffsetDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(OffsetDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public OffsetDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean hasStarted() {
        return inTreatmentStatus(IN_PROGRESS) || inTreatmentStatus(DONE);
    }


    public boolean hasFinished() {
        return inTreatmentStatus(DONE);
    }

    private boolean inTreatmentStatus(VisitTreatmentStatus visitTreatmentStatus) {
        return getTreatmentStatus().equals(visitTreatmentStatus);
    }
// tag::end-class[]
}
// end::end-class[]
