package io.jmix.petclinic.entity.visit;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum EmergencyType implements EnumClass<String> {

    TRAUMA("TRAUMA"),
    POISONING("POISONING"),
    BREATHING_ISSUES("BREATHING_ISSUES"),
    SEIZURE("SEIZURE"),
    OTHER("OTHER");

    private final String id;

    EmergencyType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static EmergencyType fromId(String id) {
        for (EmergencyType at : EmergencyType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}