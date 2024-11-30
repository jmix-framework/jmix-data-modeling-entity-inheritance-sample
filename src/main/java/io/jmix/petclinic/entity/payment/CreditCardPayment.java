package io.jmix.petclinic.entity.payment;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Table(name = "PETCLINIC_CREDIT_CARD_PAYMENT")
@JmixEntity
@Entity(name = "petclinic_CreditCardPayment")
public class CreditCardPayment extends Payment {
    @Column(name = "CARD_NUMBER", nullable = false)
    @NotNull
    private String cardNumber;

    @Column(name = "CARD_HOLDER_NAME", nullable = false)
    @NotNull
    private String cardHolderName;

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}