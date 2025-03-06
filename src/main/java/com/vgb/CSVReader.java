package com.vgb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Utility class for reading CSV files.
 */
public class CSVReader {

    /**
     * Reads persons from the Persons CSV file.
     * 
     * @path   CSV file path
     * @return List of Person objects
     * @throws IOException if there is an error reading the file
     */
    public static Map<UUID,Person> readPersons(String path) throws IOException {
        Map<UUID,Person> persons = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
            	String parts[] = line.split(",");
            	
            	try {
            	String uuidstr = parts[0].trim();
            	UUID uuid = UUID.fromString(uuidstr);
                if (uuidstr.isEmpty()) {
                    throw new IllegalArgumentException("Missing UUID in CSV: " + Arrays.toString(parts));
                }
                                
                
                String firstName = parts[1];
                String lastName = parts[2];
                String phone = parts[3];
                
                
                List<String> emails = new ArrayList<>();
                for (int i = 4; i < parts.length; i++) {
                    if (!parts[i].trim().isEmpty()) {
                        emails.add(parts[i].trim());
                    }
                }
                
                persons.put(uuid,new Person(uuid, firstName, lastName, phone,  emails));
            	} catch(IllegalArgumentException e) {
            		System.err.print(e.getStackTrace() + " Invalid UUID in CSV");
            		
            	}
                
            }
        }
        
        return persons;
    }
    
    /**
     * Reads companies from the Companies CSV file.
     * 
     * @path   CSV file path
     * @return List of Company objects
     * @throws IOException if there is an error reading the file
     */
    public static Map<UUID,Company> readCompanies(String path, Map<UUID, Person> personLookup) throws IOException {
        Map<UUID, Company> companies = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
            	 String parts[] = line.split(",");
                
                
                UUID companyUuid = UUID.fromString(parts[0].trim());
                UUID contactUuid = UUID.fromString(parts[1].trim());
                
                /**
                 * Looks up the Person with a matching uuid in personLookup
                 */
                Person contactPerson = personLookup.get(contactUuid);
                if (contactPerson == null) {
                    throw new IllegalArgumentException("Person with UUID " + contactUuid + " not found.");
                }
                String name = parts[2];
                String street = parts[3];
                String city = parts[4];
                String state = parts[5];
                String zip = parts[6];
                
                Address address = new Address(street, city, state, zip);
                
                
                companies.put(companyUuid, new Company(name, companyUuid, contactPerson, address));
            }
        }
        
        return companies;
    }
    
    
    /**
     * Reads items from the Items CSV file.
     * 
     * @path   CSV file path
     * @return List of Item objects
     * @throws IOException if there is an error reading the file
     */
    public static Map<UUID,Item> readItems(String path, Map<UUID, Company> companyLookup) throws IOException {
        Map<UUID,Item> items = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String parts[] = line.split(",");
                
                String uuidstr = parts[0].trim();
                
                if (uuidstr.isEmpty()) {
                    throw new IllegalArgumentException("Missing UUID in CSV: " + Arrays.toString(parts));
                }
                
                try {
                    UUID uuid = UUID.fromString(uuidstr);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid UUID format: " + uuidstr);
                }
                
                UUID uuid = UUID.fromString(parts[0].trim());
                char type = parts[1].charAt(0);
                String name = parts[2];
                
                switch (type) {
                    case 'E':
                        String model = parts[3];
                        double price = Double.parseDouble(parts[4]);
                        items.put(uuid,new Equipment(uuid, name, model, price));
                        break;
                    case 'M':
                        String unit = parts[3];
                        double costPerUnit = Double.parseDouble(parts[4]);
                        items.put(uuid, new Material(uuid, name, unit, costPerUnit));
                        break;
                    case 'C':
                    	
                             	
                        UUID companyUuid = UUID.fromString(parts[3].trim());
                        
                        Company company = companyLookup.get(companyUuid);
                        if (company == null) {
                            throw new IllegalArgumentException("Company with UUID " + companyUuid + " not found.");
                        }
                        items.put(uuid, new Contract(uuid, name,company));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown item type: " + type);
                }
            }
        }
        return items;
    }
    
    
public static void main(String[] args) throws IOException {
		//Debugging
	
		Equipment x = new Equipment(UUID.fromString("7af2d8f9-d09e-4992-a41d-3bec9ed2aa31"), "balls", "XXXX", 95125);
		LocalDate startDate = LocalDate.parse("2024-01-01");
		LocalDate endDate = LocalDate.parse("2026-06-01");

		LocalDateTime startTime = LocalDateTime.parse("2024-01-01T10:15:30");
		LocalDateTime endTime = LocalDateTime.parse("2024-01-02T11:15:30");

	
		AgreementFactory leaseFactory = new LeaseFactory();
		Agreement lease = leaseFactory.createAgreement(x, startDate, endDate);
		
		AgreementFactory rentalFactory = new RentalFactory();
		Agreement rental = rentalFactory.createAgreement(x,startTime , endTime);
		
		System.out.println(lease.calculateTotal());	
		System.out.println(rental.calculateTotal());
		}
  
}