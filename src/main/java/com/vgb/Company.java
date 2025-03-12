package com.vgb;

import java.util.UUID;
//TODO: create get company method that returns the object that posses the UUID thats parsed in as an argument
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

    
    public Person getContactPerson() {
        return this.contactPerson;
    }
    
    public Address getAddress() {
    	return this.address;
    }
    
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
    	return String.format("%8s (%36s)"
				+ "\n %s, %s (%36s)"
				+ "\n %s "
				+ "\n"
				+ "\n %s",this.getName(),this.getCompanyUuid(), 
				this.getContactPerson().getFirstName(),
				this.getContactPerson().getLastName(), getContactPerson().getUuid(),
				getContactPerson().getEmails(), getAddress().getFullAddress() );
    }
    
    public Company getCustomer(UUID mapUUID) {
    	if(this.companyUuid == mapUUID) {
    		return this;
    	}
    	else {
    		return null;
    	}
    }
    //TODO: Edit To string of this
}