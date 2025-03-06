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
    	Map<UUID, Person> persons = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine(); // Skip header

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                try {
                    Person person = PersonFactory.createPerson(parts);
                    persons.put(person.getUuid(), person);
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid row: " + Arrays.toString(parts) + " | Error: " + e.getMessage());
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
        CompanyFactory companyFactory = new CompanyFactory(personLookup);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
            	 String parts[] = line.split(",");
                
                 UUID companyUuid = UUID.fromString(parts[0].trim());
                 Company company = companyFactory.createCompany(parts);
                 companies.put(companyUuid, company);
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
        ItemFactory itemFactory = new ItemFactory(companyLookup);
        
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
                Class<?> itemType;
                
                switch (type){                
                case 'E': itemType = Equipment.class;
                	break;
                case 'M': itemType = Material.class;
                	break;
                case 'C': itemType = Contract.class;
                	break;
                default:
                	throw new IllegalArgumentException("Unknown item type: " + type);                
                }
                items.put(uuid, itemFactory.createItem(itemType, parts));
            }
        }
        return items;
    }
    
    
public static void main(String[] args) throws IOException {
		//Debugging
	
//		Equipment x = new Equipment(UUID.fromString("7af2d8f9-d09e-4992-a41d-3bec9ed2aa31"), "balls", "XXXX", 95125);
//		LocalDate startDate = LocalDate.parse("2024-01-01");
//		LocalDate endDate = LocalDate.parse("2026-06-01");
//
//		LocalDateTime startTime = LocalDateTime.parse("2024-01-01T10:15:30");
//		LocalDateTime endTime = LocalDateTime.parse("2024-01-02T11:15:30");
//
//	
//		AgreementFactory leaseFactory = new LeaseFactory();
//		Agreement lease = leaseFactory.createAgreement(x, startDate, endDate);
//		
//		AgreementFactory rentalFactory = new RentalFactory();
//		Agreement rental = rentalFactory.createAgreement(x,startTime , endTime);
//		
//		System.out.println(lease.calculateTotal());	
//		System.out.println(rental.calculateTotal());
		
//		System.out.println(x.getType());
	
	
	System.out.println(readItems("data/Items.csv", readCompanies("data/Companies.csv", readPersons("data/Persons.csv"))));
	}
  
}