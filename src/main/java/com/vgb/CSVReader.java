package com.vgb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public static List<Person> readPersons(String path) throws IOException {
        List<Person> persons = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
            	String parts[] = line.split(",");
            	
            	String uuidstr = parts[0].trim().replaceAll("[\"]", "");
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
                
                persons.add(new Person(uuid, firstName, lastName, phone,  emails));
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
    public static List<Company> readCompanies(String path) throws IOException {
        List<Company> companies = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
            	 String parts[] = line.split(",");
                
                
                UUID companyUuid = UUID.fromString(parts[0].trim());
                UUID contactUuid = UUID.fromString(parts[1].trim());
                String name = parts[2];
                String street = parts[3];
                String city = parts[4];
                String state = parts[5];
                String zip = parts[6];
                
                // Updated to match Company constructor parameter names
                companies.add(new Company(companyUuid, contactUuid, name, street, city, state, zip));
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
    public static List<Item> readItems(String path) throws IOException {
        List<Item> items = new ArrayList<>();
        
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
                        items.add(new Equipment(uuid, name, model, price));
                        break;
                    case 'M':
                        String unit = parts[3];
                        double costPerUnit = Double.parseDouble(parts[4]);
                        items.add(new Material(uuid, name, unit, costPerUnit));
                        break;
                    case 'C':
                        UUID companyUuid = UUID.fromString(parts[3].trim());
                        items.add(new Contract(uuid, name, companyUuid));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown item type: " + type);
                }
            }
        }
        return items;
    }
    
    
public static void main(String[] args) throws IOException {
		
	System.out.println(readPersons("data/Persons.csv"));
		
		
		
		}
  
}