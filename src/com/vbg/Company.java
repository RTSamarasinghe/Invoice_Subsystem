package com.vbg;

import java.util.UUID;

/**
 * Represents a company in the system.
 * Contains company information such as name, address, and contact person.
 */
public class Company {
    private final UUID companyUuid;
    private final UUID contactUuid;
    private final String name;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;

    /**
     * Constructs a Company with the given attributes
     * 
     * @param companyUuid        The UUID of the company
     * @param contactUuid The UUID of the contact person
     * @param name        The name of the company
     * @param street      The street address of the company
     * @param city        The city of the company's address
     * @param state       The state of the company's address
     * @param zip         The ZIP code of the company's address
     */
    
    public Company(UUID companyUuid, UUID contactUuid, String name,
                   String street, String city, String state, String zip) {
        this.companyUuid = companyUuid;
        this.contactUuid = contactUuid;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    
    public UUID getCompanyUuid() {
        return companyUuid;
    }

    
    public UUID getContactUuid() {
        return contactUuid;
    }

    
    public String getName() {
        return name;
    }

    
    public String getFullAddress() {
        return street + ", " + city + ", " + state + " " + zip;
    }

    
    public String getStreet() {
        return street;
    }

    
    public String getCity() {
        return city;
    }

   
    public String getState() {
        return state;
    }

    
    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyUuid=" + companyUuid +
                ", name='" + name + '\'' +
                ", address='" + getFullAddress() + '\'' +
                ", contactUuid=" + contactUuid +
                '}';
    }
}