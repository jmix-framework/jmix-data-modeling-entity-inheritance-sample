package io.jmix.petclinic.visit;

import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.core.TimeSource;
import io.jmix.petclinic.EmployeeRepository;
import io.jmix.petclinic.entity.User;
import io.jmix.petclinic.entity.payment.*;
import io.jmix.petclinic.entity.pet.Pet;
import io.jmix.petclinic.entity.visit.Visit;
import io.jmix.petclinic.entity.visit.VisitTreatmentStatus;
import io.jmix.petclinic.entity.visit.VisitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component("petclinic_VisitTestDataCreation")
public class VisitTestDataCreation {
    private static final Logger log = LoggerFactory.getLogger(VisitTestDataCreation.class);

    protected final PetclinicTestdataProperties petclinicTestdataProperties;
    protected final TimeSource timeSource;
    protected final DataManager dataManager;
    protected final RandomVisitDateTime randomVisitDateTime;
    private final EmployeeRepository employeeRepository;

    public VisitTestDataCreation(
            PetclinicTestdataProperties petclinicTestdataProperties,
            TimeSource timeSource,
            DataManager dataManager,
            RandomVisitDateTime randomVisitDateTime,
            EmployeeRepository employeeRepository) {
        this.petclinicTestdataProperties = petclinicTestdataProperties;
        this.timeSource = timeSource;
        this.dataManager = dataManager;
        this.randomVisitDateTime = randomVisitDateTime;
        this.employeeRepository = employeeRepository;
    }

    public void createData() {

        if (visitsExists()) {
            log.info("Visits found in DB. Visit Test data generation is skipped...");
            return;
        }

        log.info("No Visits found in the DB. Visit Test data will be created...");

        List<Visit> visits = createVisits();
        int visitCreatedCount = commit(visits);

        String visitsCreatedMessage = String.format("%d Visits created", visitCreatedCount);

        log.info(visitsCreatedMessage);

        createInsuranceProviders();


        createInvoicesAndPayments(visits);

    }

    private void createInsuranceProviders() {
        // Prüfen, ob bereits Insurance Provider vorhanden sind
        if (!list(InsuranceProvider.class).isEmpty()) {
            log.info("Insurance Providers already exist. Skipping creation...");
            return;
        }

        log.info("Creating example Insurance Providers...");

        // Beispielnamen für Insurance Provider
        List<String> providerNames = List.of(
                "PetCare Insurance",
                "PawSafe Assurance",
                "Animal Wellness Group",
                "Happy Tails Insurance",
                "VetSecure Coverage",
                "FurSure Protection",
                "PetShield Services",
                "Purrfect Policy",
                "Critter Care Insurance",
                "Tail Wag Assurance"
        );

        List<InsuranceProvider> providers = providerNames.stream()
                .map(name -> {
                    InsuranceProvider provider = dataManager.create(InsuranceProvider.class);
                    provider.setName(name);
                    return provider;
                })
                .toList();

        commit(providers);

        log.info("{} Insurance Providers created", providers.size());
    }

    private void createInvoicesAndPayments(List<Visit> visits) {
        List<Invoice> invoices = visits.stream()
                .map(this::createInvoiceForVisit)
                .toList();
        commit(invoices);

        List<Payment> payments = invoices.stream()
                .map(this::createPaymentForInvoice)
                .toList();
        commit(payments);

        log.info("{} Invoices and {} Payments created", invoices.size(), payments.size());
    }

    private Invoice createInvoiceForVisit(Visit visit) {
        Invoice invoice = dataManager.create(Invoice.class);

        invoice.setVisit(visit);
        invoice.setInvoiceDate(visit.getVisitEnd().toLocalDate());
        invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        return invoice;
    }

    private Payment createPaymentForInvoice(Invoice invoice) {
        Payment payment;

        int paymentType = random().nextInt(3);
        switch (paymentType) {
            case 0 -> payment = createCashPayment(invoice);
            case 1 -> payment = createCreditCardPayment(invoice);
            case 2 -> payment = createInsurancePayment(invoice);
            default -> throw new IllegalStateException("Unexpected payment type");
        }

        return payment;
    }

    private CashPayment createCashPayment(Invoice invoice) {
        CashPayment payment = dataManager.create(CashPayment.class);

        payment.setInvoice(invoice);
        payment.setPaymentDate(randomPaymentDate());
        BigDecimal amount = randomAmount();
        payment.setAmount(amount);
        payment.setAmountTendered(amount.add(new BigDecimal("10.00")));
        payment.setChangeGiven(new BigDecimal("10.00"));

        return payment;
    }

    private CreditCardPayment createCreditCardPayment(Invoice invoice) {
        CreditCardPayment payment = dataManager.create(CreditCardPayment.class);

        payment.setInvoice(invoice);
        payment.setPaymentDate(randomPaymentDate());
        BigDecimal amount = randomAmount();
        payment.setAmount(amount);

        // Karteninhaber ist der Owner des Pets, das dem Visit zugeordnet ist
        Visit visit = invoice.getVisit();
        String cardHolderName = visit.getPet().getOwner().getFullName();
        payment.setCardHolderName(cardHolderName);

        // Zufällige Kreditkartennummer generieren
        payment.setCardNumber(generateRandomCardNumber());

        return payment;
    }

    private InsurancePayment createInsurancePayment(Invoice invoice) {
        InsurancePayment payment = dataManager.create(InsurancePayment.class);

        payment.setInvoice(invoice);
        payment.setPaymentDate(randomPaymentDate());
        BigDecimal amount = randomAmount();
        payment.setAmount(amount);

        payment.setPolicyNumber("POLICY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        payment.setInsuranceProvider(randomInsuranceProvider());

        return payment;
    }

    private LocalDateTime randomPaymentDate() {
        return LocalDateTime.now().minusDays(random().nextInt(30));
    }

    private BigDecimal randomAmount() {
        int randomCents = random().nextInt(45000) + 5000; // Werte von 5000 bis 50000 in Cent
        return new BigDecimal(randomCents).divide(new BigDecimal("100"));
    }

    private String generateRandomCardNumber() {
        return random().ints(16, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    private InsuranceProvider randomInsuranceProvider() {
        List<InsuranceProvider> providers = list(InsuranceProvider.class);
        if (providers.isEmpty()) {
            throw new IllegalStateException("No Insurance Providers found for test data generation.");
        }
        return randomOfList(providers);
    }

    List<Visit> createVisits() {
        final List<User> allNurses = employeeRepository.findAllNurses();
        final List<Pet> allPets = list(Pet.class);

        return Stream.concat(
                createPastVisits(allPets, allNurses),
                createFutureVisits(allPets, allNurses)
        )
                .collect(Collectors.toList());
    }

    private Stream<Visit> createPastVisits(List<Pet> possiblePets, List<User> possibleNurses) {
        return IntStream.range(0, petclinicTestdataProperties.getVisitStartAmountPastDays())
                .mapToObj(value -> timeSource.now().minusDays(value).toLocalDate())
                .map(localDate -> createVisitsForDate(localDate, possiblePets, possibleNurses))
                .flatMap(Collection::stream)
                .filter(Objects::nonNull);
    }

    private Stream<Visit> createFutureVisits(List<Pet> possiblePets, List<User> possibleNurses) {
        return IntStream.range(1, petclinicTestdataProperties.getVisitStartAmountFutureDays() + 1)
                .mapToObj(i -> createVisitsForDate(
                        timeSource.now().plusDays(i).toLocalDate(),
                        amountForFutureDate(i),
                        possiblePets,
                        possibleNurses
                ))
                .flatMap(Collection::stream)
                .filter(Objects::nonNull);
    }

    private int amountForFutureDate(int i) {
        int max = petclinicTestdataProperties.getVisitStartAmountFutureDays() + 1;
        return (int) ((double) (max - i) / max * petclinicTestdataProperties.getAmountPerDay());
    }

    private List<Visit> createVisitsForDate(LocalDate localDate, List<Pet> possiblePets, List<User> possibleNurses) {
        return IntStream.range(0, petclinicTestdataProperties.getAmountPerDay())
                .mapToObj(i -> createVisit(localDate, possiblePets, possibleNurses))
                .collect(Collectors.toList());
    }

    private List<Visit> createVisitsForDate(LocalDate localDate, int amount, List<Pet> possiblePets, List<User> possibleNurses) {
        return IntStream.range(0, amount)
                .mapToObj(i -> createVisit(localDate, possiblePets, possibleNurses))
                .collect(Collectors.toList());
    }

    private boolean visitsExists() {
        return !list(Visit.class).isEmpty();
    }

    private <T> int commit(List<T> entities) {
        SaveContext saveContext = new SaveContext();
        entities.forEach(saveContext::saving);
        dataManager.save(saveContext);

        return entities.size();
    }

    Visit createVisit(
        LocalDate date,
        List<Pet> possiblePets,
        List<User> possibleNurses
    ) {

        VisitEventRange visitEventRange = randomVisitDateTime.randomVisitEventRange(date);

        if (visitEventRange.isEmpty()) {
            return null;
        }

        Visit visit = dataManager.create(Visit.class);

        visit.setTreatmentStatus(treatmentStatusFor(date));

        if (nurseShouldBeAssigned(date)) {
            visit.setAssignedNurse(randomOfList(possibleNurses));
        }

        visit.setPet(randomOfList(possiblePets));
        visit.setType(randomVisitType());
        visit.setDescription(randomDescription());

        visit.setVisitStart(visitEventRange.getVisitStart());
        visit.setVisitEnd(visitEventRange.getVisitEnd());

        return visit;
    }

    private boolean nurseShouldBeAssigned(LocalDate date) {
        return date.isBefore(timeSource.now().toLocalDate().plusWeeks(1).plusDays(1));
    }

    private VisitTreatmentStatus treatmentStatusFor(LocalDate date) {
        final LocalDate today = timeSource.now().toLocalDate();
        if (date.equals(today)) {
            return VisitTreatmentStatus.IN_PROGRESS;
        }
        else if (date.isAfter(today)) {
            return VisitTreatmentStatus.UPCOMING;
        }
        else {
            return VisitTreatmentStatus.DONE;
        }
    }

    private VisitType randomVisitType() {
        int pick = random().nextInt(VisitType.values().length);
        return VisitType.values()[pick];
    }

    private String randomDescription() {
        return randomOfList(
                petclinicTestdataProperties.getDescriptionOptions()
        ).trim();
    }

    private <T> T randomOfList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(random().nextInt(list.size()));
    }

    private Random random() {
        return new Random();
    }

    private <T> List<T> list(Class<T> entityClass) {
        return dataManager.load(entityClass).all().list();
    }

}