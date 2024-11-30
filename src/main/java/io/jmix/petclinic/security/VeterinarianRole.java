package io.jmix.petclinic.security;

import io.jmix.petclinic.entity.User;
import io.jmix.petclinic.entity.owner.Owner;
import io.jmix.petclinic.entity.payment.*;
import io.jmix.petclinic.entity.pet.Bird;
import io.jmix.petclinic.entity.pet.Cat;
import io.jmix.petclinic.entity.pet.Pet;
import io.jmix.petclinic.entity.pet.PetType;
import io.jmix.petclinic.entity.veterinarian.Specialty;
import io.jmix.petclinic.entity.veterinarian.Veterinarian;
import io.jmix.petclinic.entity.visit.EmergencyVisit;
import io.jmix.petclinic.entity.visit.FollowUpVisit;
import io.jmix.petclinic.entity.visit.RegularCheckup;
import io.jmix.petclinic.entity.visit.Visit;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Veterinarian", code = "Veterinarian")
public interface VeterinarianRole {
    @EntityAttributePolicy(entityClass = Visit.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Visit.class, actions = EntityPolicyAction.ALL)
    void visit();

    @EntityAttributePolicy(entityClass = Pet.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Pet.class, actions = EntityPolicyAction.ALL)
    void pet();

    @EntityAttributePolicy(entityClass = Owner.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Owner.class, actions = EntityPolicyAction.ALL)
    void owner();

    @EntityAttributePolicy(entityClass = PetType.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PetType.class, actions = EntityPolicyAction.ALL)
    void petType();

    @EntityAttributePolicy(entityClass = Specialty.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Specialty.class, actions = EntityPolicyAction.ALL)
    void specialty();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Veterinarian.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Veterinarian.class, actions = EntityPolicyAction.ALL)
    void veterinarian();

    @MenuPolicy(menuIds = {"petclinic_Pet.list", "petclinic_Owner.list", "petclinic_Visit.list", "petclinic_Specialty.list", "petclinic_Veterinarian.list", "petclinic_PetType.list", "petclinic_Payment.list"})
    @ViewPolicy(viewIds = {"petclinic_Visit.list", "petclinic_Pet.list", "petclinic_Owner.list", "petclinic_Veterinarian.list", "petclinic_Specialty.list", "petclinic_PetType.list", "petclinic_Visit.detail", "petclinic_Veterinarian.detail", "petclinic_Pet.detail", "petclinic_Owner.detail", "petclinic_Cat.detail", "petclinic_Bird.detail", "petclinic_EmergencyVisit.detail", "petclinic_FollowUpVisit.detail", "petclinic_RegularCheckup.detail", "petclinic_Payment.list", "petclinic_Payment.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = CashPayment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = CashPayment.class, actions = EntityPolicyAction.ALL)
    void cashPayment();

    @EntityAttributePolicy(entityClass = Cat.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Cat.class, actions = EntityPolicyAction.ALL)
    void cat();

    @EntityAttributePolicy(entityClass = Bird.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Bird.class, actions = EntityPolicyAction.ALL)
    void bird();

    @EntityAttributePolicy(entityClass = EmergencyVisit.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = EmergencyVisit.class, actions = EntityPolicyAction.ALL)
    void emergencyVisit();

    @EntityAttributePolicy(entityClass = CreditCardPayment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = CreditCardPayment.class, actions = EntityPolicyAction.ALL)
    void creditCardPayment();

    @EntityAttributePolicy(entityClass = FollowUpVisit.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = FollowUpVisit.class, actions = EntityPolicyAction.ALL)
    void followUpVisit();

    @EntityAttributePolicy(entityClass = InsuranceProvider.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = InsuranceProvider.class, actions = EntityPolicyAction.ALL)
    void insuranceProvider();

    @EntityAttributePolicy(entityClass = InsurancePayment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = InsurancePayment.class, actions = EntityPolicyAction.ALL)
    void insurancePayment();

    @EntityAttributePolicy(entityClass = Invoice.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Invoice.class, actions = EntityPolicyAction.ALL)
    void invoice();

    @EntityAttributePolicy(entityClass = RegularCheckup.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RegularCheckup.class, actions = EntityPolicyAction.ALL)
    void regularCheckup();

    @EntityAttributePolicy(entityClass = Payment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Payment.class, actions = EntityPolicyAction.ALL)
    void payment();
}