package io.jmix.petclinic.user;

import io.jmix.core.DataManager;
import io.jmix.core.Sort;
import io.jmix.petclinic.entity.payment.*;
import io.jmix.petclinic.test_support.AuthenticatedAsAdmin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(AuthenticatedAsAdmin.class)
public class PaymentTest {

    @Autowired
    private DataManager dataManager;

    @PersistenceContext
    private EntityManager entityManager;

    private final List<Object> createEntities = new ArrayList<>();

    @BeforeEach
    void setUp() {

        dataManager.load(Payment.class).all().list().forEach(dataManager::remove);

        InsuranceProvider insuranceProvider = dataManager.create(InsuranceProvider.class);
        insuranceProvider.setName("Insurance Provider");

        createEntities.add(insuranceProvider);

        for (int i = 0; i < 10; i++) {

            Invoice invoice = dataManager.create(Invoice.class);
            invoice.setInvoiceDate(LocalDate.now());
            invoice.setInvoiceNumber(String.valueOf(i));

            createEntities.add(invoice);

            CashPayment cashPayment = dataManager.create(CashPayment.class);
            cashPayment.setPaymentDate(LocalDate.now().atStartOfDay());
            cashPayment.setInvoice(invoice);
            cashPayment.setAmount(new BigDecimal("100.0"));
            cashPayment.setAmountTendered(new BigDecimal("20.0"));
            cashPayment.setChangeGiven(new BigDecimal("20.0"));
            createEntities.add(cashPayment);

            CreditCardPayment creditCardPayment = dataManager.create(CreditCardPayment.class);
            creditCardPayment.setAmount(new BigDecimal("200.0"));
            creditCardPayment.setPaymentDate(LocalDate.now().atStartOfDay());
            creditCardPayment.setInvoice(invoice);
            creditCardPayment.setCardNumber(String.valueOf(i));
            creditCardPayment.setCardHolderName("Card Holder Name");
            createEntities.add(creditCardPayment);

            InsurancePayment insurancePayment = dataManager.create(InsurancePayment.class);
            insurancePayment.setAmount(new BigDecimal("300.0"));
            insurancePayment.setPaymentDate(LocalDate.now().atStartOfDay());
            insurancePayment.setInvoice(invoice);
            insurancePayment.setInsuranceProvider(insuranceProvider);
            insurancePayment.setPolicyNumber(String.valueOf(i));
            createEntities.add(insurancePayment);
        }

        dataManager.saveAll(createEntities);
    }

    @Nested
    class EntityManagerUsage {

        @Test
        @DisplayName("Without sorting it works - as expected")
        void test_jmixEntityManager_query() {
            // when:
            List<Payment> results = entityManager.createQuery("SELECT p FROM petclinic_Payment p", Payment.class)
                    .getResultList();

            // then:
            assertThat(results).isNotEmpty();
        }


        @Test
        @DisplayName("With sorting it does not work - NOT as expected")
        void test_jmixEntityManager_withSorting_query() {
            // when:
            List<Payment> results = entityManager.createQuery("SELECT p FROM petclinic_Payment p order by p.paymentDate", Payment.class)
                    .getResultList();

            // then:
            assertThat(results).isNotEmpty();
        }
    }

    @Nested
    class DelegateEntityManagerUsage {

        @Test
        @Transactional
        @DisplayName("Without sorting it works - as expected")
        void test_delegateEntityManager_query() {

            // given:
            EntityManager delegate = (EntityManager) entityManager.getDelegate();

            // when:
            List<Payment> results = delegate.createQuery("SELECT p FROM petclinic_Payment p", Payment.class)
                    .getResultList();

            // then:
            assertThat(results).isNotEmpty();
        }

        @Transactional
        @Test
        @DisplayName("With sorting it does not work - NOT as expected")
        void test_delegateEntityManager_withSorting_query() {

            // given:
            EntityManager delegate = (EntityManager) entityManager.getDelegate();

            // when:
            List<Payment> results = delegate.createQuery("SELECT p FROM petclinic_Payment p order by p.paymentDate", Payment.class)
                    .getResultList();

            // then:
            assertThat(results).isNotEmpty();
        }
    }

    @Nested
    class DataManagerUsage {

        @Test
        @DisplayName("Without sorting it works - as expected")
        void test_dataManagerWithoutSort_query() {
            // when:
            List<Payment> results = dataManager.load(Payment.class)
                    .all()
                    .list();

            // then:
            assertThat(results).isNotEmpty();
        }

        @Test
        @DisplayName("With sorting it does not work - NOT as expected")
        void test_dataManager_withSorting_query() {
            // when:
            List<Payment> results = dataManager.load(Payment.class)
                    .all()
                    .sort(Sort.by("paymentDate"))
                    .list();

            // then:
            assertThat(results).isNotEmpty();
        }
    }

    @AfterEach
    void tearDown() {
        createEntities.forEach(dataManager::remove);
    }
}