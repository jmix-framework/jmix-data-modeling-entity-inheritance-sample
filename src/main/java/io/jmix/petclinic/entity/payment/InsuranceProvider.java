package io.jmix.petclinic.entity.payment;

import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.petclinic.entity.NamedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@JmixEntity
@Table(name = "PETCLINIC_INSURANCE_PROVIDER")
@Entity(name = "petclinic_InsuranceProvider")
public class InsuranceProvider extends NamedEntity {
}