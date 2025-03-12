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
//TODO: Create readInvoiceItem  method
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
    
    public static Map<UUID,Invoice> readInvoice(String path, Map<UUID, Person> personLookup, Map <UUID, Company> companyLookup) throws IOException {
    	Map<UUID, Invoice> invoices = new HashMap<>();
    	InvoiceFactory invoicefactory = new InvoiceFactory(personLookup, companyLookup);
    	
    	
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine(); // Skip header

            String line;
            
            
            while ((line = reader.readLine()) != null) {
            	
            	if (line.isEmpty()) {
                    continue; 
                }
                String[] parts = line.split(",");
                
                try {
                    Invoice invoice = invoicefactory.createInvoice(parts);
                    invoices.put(invoice.getInvoiceUUID(), invoice);
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid row: " + Arrays.toString(parts) + " | Error: " + e.getMessage());
                }
            }
        }

        return invoices;
    }
    
    public static void main(String[] args) {
    //Debug
    	try {
    	Map<UUID, Person> person = readPersons("data/persons.csv");
    	Map<UUID, Company> companies = readCompanies("data/Companies.csv", person);
    	
		System.out.println(readInvoice("data/Invoices.csv", person, companies));
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
}