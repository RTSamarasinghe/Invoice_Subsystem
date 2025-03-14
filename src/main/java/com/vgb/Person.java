package com.vgb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
//TODO: Edit toString
/**
 * Represents a person in the system.
 * Contains personal information such as name, email addresses, etc.
 */
public class Person {
    private final UUID uuid;
    private final String firstName;
    private final String lastName;
	private final String phone;
    private final List<String> emails;

    /**
     * Constructs a Person with the given attributes
     * 
     * @param uuid      The UUID of the person
     * @param firstName The first name of the person
     * @param lastName  The last name of the person
     * @param emails    List of email addresses associated with the person
     */
   
    public Person(UUID uuid, String firstName, String lastName, String phone, List<String> emails) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.emails = new ArrayList<>(emails);
    }

    
    public UUID getUuid() {
        return uuid;
    }

   
    public String getFirstName() {
        return firstName;
    }

    
    public String getLastName() {
        return lastName;
    }

    
    public String getFullName() {
        return firstName + ", " + lastName;
    }

    
    public List<String> getEmails() {
        return Collections.unmodifiableList(emails);
    }
    
    public String getPhone() {
		return phone;
	}


  
}