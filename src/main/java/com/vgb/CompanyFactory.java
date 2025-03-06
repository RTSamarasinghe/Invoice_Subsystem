package com.vgb;

import java.util.Map;
import java.util.UUID;

public class CompanyFactory {
	
    private final Map<UUID, Person> personLookup;

    public CompanyFactory(Map<UUID, Person> personLookup) {
        this.personLookup = personLookup;
    }

    public Company createCompany(String[] parts) {
        UUID companyUuid = UUID.fromString(parts[0].trim());
        UUID contactUuid = UUID.fromString(parts[1].trim());

        Person contactPerson = personLookup.get(contactUuid);
        if (contactPerson == null) {
            throw new IllegalArgumentException("Person with UUID " + contactUuid + " not found.");
        }

        String name = parts[2];
        Address address = new Address(parts[3], parts[4], parts[5], parts[6]);

        return new Company(name, companyUuid, contactPerson, address);
    }

}
