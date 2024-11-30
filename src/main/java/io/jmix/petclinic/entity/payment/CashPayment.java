package io.jmix.petclinic.entity.payment;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JmixEntity
@Table(name = "PETCLINIC_CASH_PAYMENT")
@Entity(name = "petclinic_CashPayment")
public class CashPayment extends Payment {
    @Column(name = "AMOUNT_TENDERED", nullable = false, precision = 19, scale = 2)
    @NotNull
    private BigDecimal amountTendered;

    @Column(name = "CHANGE_GIVEN", precision = 19, scale = 2)
    private BigDecimal changeGiven;

    public BigDecimal getChangeGiven() {
        return changeGiven;
    }

    public void setChangeGiven(BigDecimal changeGiven) {
        this.changeGiven = changeGiven;
    }

    public BigDecimal getAmountTendered() {
        return amountTendered;
    }

    public void setAmountTendered(BigDecimal amountTendered) {
        this.amountTendered = amountTendered;
    }
}