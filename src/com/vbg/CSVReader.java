package com.vbg;

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
     * @return List of Person objects
     * @throws IOException if there is an error reading the file
     */
    public static List<Person> readPersons() throws IOException {
        List<Person> persons = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("data/Persons.csv"))) {
            // Skip header
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = parseCsvLine(line);
                
                UUID uuid = UUID.fromString(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                String phone = parts[3];
                
                // Parse emails (might be empty)
                List<String> emails = new ArrayList<>();
                for (int i = 4; i < parts.length; i++) {
                    if (!parts[i].trim().isEmpty()) {
                        emails.add(parts[i]);
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
     * @return List of Company objects
     * @throws IOException if there is an error reading the file
     */
    public static List<Company> readCompanies() throws IOException {
        List<Company> companies = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("data/Companies.csv"))) {
            // Skip header
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = parseCsvLine(line);
                
                
                UUID companyUuid = UUID.fromString(parts[0]);
                UUID contactUuid = UUID.fromString(parts[1]);
                String name = parts[2];
                String street = parts[3];
                String city = parts[4];
                String state = parts[5];
                String zip = parts[6];
                
                
                companies.add(new Company(companyUuid, contactUuid, name, street, city, state, zip));
            }
        }
        
        return companies;
    }
    
    /**
     * Reads items from the Items CSV file.
     * 
     * @return List of Item objects
     * @throws IOException if there is an error reading the file
     */
    public static List<Item> readItems() throws IOException {
        List<Item> items = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("data/Items.csv"))) {
            // Skip header
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = parseCsvLine(line);
                
                UUID uuid = UUID.fromString(parts[0]);
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
                        UUID companyUuid = UUID.fromString(parts[3]);
                        items.add(new Contract(uuid, name, companyUuid));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown item type: " + type);
                }
            }
        }
        
        return items;
    }
    
    /**
     * Parses a CSV line, handling potential commas within fields.
     * 
     * @param line The CSV line to parse
     * @return Array of field values
     */
    private static String[] parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;
        
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }
        
        // Add the last field
        result.add(field.toString());
        
        return result.toArray(new String[0]);
    }
}