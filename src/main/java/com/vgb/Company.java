package com.vgb;

import java.util.UUID;

/**
 * Represents a company in the system.
 * Contains company information such as name, address, and contact person.
 */
public class Company {
	private String name;
    private final UUID companyUuid;
    private Person contactPerson;
    private Address address;
    

    /**
     * Constructs a Company with the given attributes
     * 
     * @param companyUuid The UUID of the company
     * @param contactPerson The UUID of the contact person
     * @param name        The name of the company
     */
    
    public Company(String name, UUID companyUuid, Person person, Address address) {
    	this.name = name;
        this.companyUuid = companyUuid;
        this.contactPerson = person;
        this.address = address;
    }

    
    public UUID getCompanyUuid() {
        return this.companyUuid;
    }

    
    public UUID getContactUuid() {
        return this.contactPerson.getUuid();
    }
    
    public Address getAddress() {
    	return this.address;
    }
    
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyUuid=" + companyUuid +
                ", name='" + name + '\'' +
                ", address='" + address.getFullAddress() + '\'' +
                ", contactUuid=" + contactPerson +
                '}';
    }
}