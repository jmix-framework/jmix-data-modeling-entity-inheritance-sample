package io.jmix.petclinic.entity.payment;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "PETCLINIC_INSURANCE_PAYMENT", indexes = {
        @Index(name = "IDX_PETCLINIC_INSURANCE_PAYMENT_INSURANCE_PROVIDER", columnList = "INSURANCE_PROVIDER_ID")
})
@Entity(name = "petclinic_InsurancePayment")
public class InsurancePayment extends Payment {
    @JoinColumn(name = "INSURANCE_PROVIDER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InsuranceProvider insuranceProvider;

    @Column(name = "POLICY_NUMBER", nullable = false)
    @NotNull
    private String policyNumber;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public InsuranceProvider getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(InsuranceProvider insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }
}