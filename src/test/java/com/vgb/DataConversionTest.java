package com.vgb;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.vgb.Company;
import com.vgb.DataConverter;
import com.vgb.Item;
import com.vgb.Person;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This is a suite of tests for the various {@link #DataConverter} derived classes.
 */


public class DataConversionTest {

	private static final String PERSON_JSON = "data/Persons_test.json";
    private static final String ITEM_JSON = "data/Items_test.json";
    private static final String COMPANY_JSON = "data/Companies_test.json";

    private static final String PERSON_XML = "data/Persons_test.xml";
    private static final String ITEM_XML = "data/Items_test.xml";
    private static final String COMPANY_XML = "data/Companies_test.xml";
    
    
    @BeforeEach
    public void clean() {
        deleteTestFiles();
    }
    
    /**
     * Tests CSV to JSON conversion for Person data
     */
    @Test
    public void testConvertToJsonPerson() throws IOException {
    	
    	DataConverter.writeToJSON("data/Persons.csv", PERSON_JSON, Person.class);

        
        File jsonFile = new File(PERSON_JSON);
        assertTrue(jsonFile.exists(), "JSON file was not created.");
        assertTrue(jsonFile.length() > 0, "JSON file is empty.");

        
        String jsonContent = new String(Files.readAllBytes(Paths.get(PERSON_JSON)));
        
        
        assertTrue(jsonContent.contains("Gaultiero"), "Expected name not found in JSON.");
        assertTrue(jsonContent.contains("Betjes"), "Expected surname not found in JSON.");
    }
    
    /**
     * Tests CSV to JSON conversion for Item data
     */
    @Test
    public void testConvertToJsonItem() throws IOException {
   
         DataConverter.writeToJSON("data/Items.csv",ITEM_JSON, Item.class);

        
        File jsonFile = new File(ITEM_JSON);
        assertTrue(jsonFile.exists(), "JSON file was not created.");
        assertTrue(jsonFile.length() > 0, "JSON file is empty.");

        String jsonContent = new String(Files.readAllBytes(Paths.get(ITEM_JSON)));
        assertTrue(jsonContent.contains("Excavator"), "Expected item name not found in JSON.");
        assertTrue(jsonContent.contains("EX900Max"), "Expected item description not found in JSON.");
    }
    
    /**
     * Tests CSV to JSON conversion for Company data
     */
    @Test
    public void testConvertToJsonCompany() throws IOException {
    	
        
        DataConverter.writeToJSON("data/Companies.csv",COMPANY_JSON, Company.class);

        
        File jsonFile = new File(COMPANY_JSON);
        assertTrue(jsonFile.exists(), "JSON file was not created.");
        assertTrue(jsonFile.length() > 0, "JSON file is empty.");

        String jsonContent = new String(Files.readAllBytes(Paths.get(COMPANY_JSON)));
        assertTrue(jsonContent.contains("5119aca1-1b5b-4ce9-ba85-4a4ed392ddc3"), "Expected Company UUID not found in JSON.");
        assertTrue(jsonContent.contains("Voonix"), "Expected Company name not found in JSON.");
    }

    /**
     * Tests CSV to XML conversion for Company data
     */
    @Test
    public void testConvertToXMLCompany() throws IOException {
       
        DataConverter.writeToXML("data/Companies.csv",COMPANY_XML, Company.class);
     
        File xmlFile = new File(COMPANY_XML);
        assertTrue(xmlFile.exists(), "XML file was not created.");
        assertTrue(xmlFile.length() > 0, "XML file is empty.");

        
        String xmlContent = new String(Files.readAllBytes(Paths.get(COMPANY_XML)));
        assertTrue(xmlContent.contains("5119aca1-1b5b-4ce9-ba85-4a4ed392ddc3"), "Expected company UUID not found in XML.");
        assertTrue(xmlContent.contains("Voonix"), "Expected company address not found in XML.");
    }
    
    /**
     * Tests CSV to XML conversion for Item data
     */
    @Test
    public void testConvertToXMLItem() throws IOException {
       
        DataConverter.writeToXML("data/Items.csv",ITEM_XML, Item.class);
     
        File xmlFile = new File(ITEM_XML);
        assertTrue(xmlFile.exists(), "XML file was not created.");
        assertTrue(xmlFile.length() > 0, "XML file is empty.");

        
        String xmlContent = new String(Files.readAllBytes(Paths.get(ITEM_XML)));
        assertTrue(xmlContent.contains("Excavator"), "Expected Item name not found in XML.");
        assertTrue(xmlContent.contains("EX900Max"), "Expected Equipment Model number not found in XML.");
    }
    
    /**
     * Tests CSV to XML conversion for Person data
     */
    @Test
    public void testWriteToXMLPerson() throws IOException {
       
        DataConverter.writeToXML("data/Persons.csv",PERSON_XML, Person.class);
     
        File xmlFile = new File(PERSON_XML);
        assertTrue(xmlFile.exists(), "XML file was not created.");
        assertTrue(xmlFile.length() > 0, "XML file is empty.");

        
        String xmlContent = new String(Files.readAllBytes(Paths.get(PERSON_XML)));
        assertTrue(xmlContent.contains("Gaultiero"), "Expected Person first name not found in XML.");
        assertTrue(xmlContent.contains("Betjes"), "Expected Person lastname not found in XML.");
    }
    
    
	private void deleteTestFiles() {
		deleteFile(PERSON_JSON);
        deleteFile(ITEM_JSON);
        deleteFile(COMPANY_JSON);
        deleteFile(PERSON_XML);
        deleteFile(ITEM_XML);
        deleteFile(COMPANY_XML);
		
	}
	
	private void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
	}
	
	
}
