package com.vbg;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a suite of tests for the various {@link #CSVReader} derived classes.
 * The compiler errors should be addressed once you've completed the lab.
 * 
 */

public class CSVReaderTest {

	/**
	 * Tests for correct data parsing in Person.class
	 * Only the first two objects will be tested
	 * @throws IOException
	 */
	@Test
	public void readPersonTest() throws IOException{
		
		 String testCsvPath = Paths.get("data/Persons.csv").toString();
		 
		 List<Person> persons = CSVReader.readPersons(testCsvPath);
		 	 
		 assertNotNull(persons, "List should not be empty");
		 assertEquals(10, persons.size(), "Should contain exactly 10 persons");
		 
		 assertEquals("ab4c9827-0f2b-4671-ba48-3b5e7058ace9", persons.get(0).getUuid().toString(), "Person UUID Mismatch");
		 assertEquals("Gaultiero", persons.get(0).getFirstName(), "Person FirstName mismatch");
		 assertEquals("Betjes", persons.get(0).getLastName(), "Person laastName mismatch");
		 assertEquals("874-796-5942", persons.get(0).getPhone(), "Person Phone mismatch");
		 assertEquals("gbetjes0@nasa.gov", persons.get(0).getEmails().get(0), "Person Email mismatch");
		 
		 assertEquals("c70e5b3f-7b38-40f7-9bfe-13359e326498", persons.get(1).getUuid().toString(), "Person UUID Mismatch");
		 assertEquals("Carolyne", persons.get(1).getFirstName(), "Person firstName mismatch");
		 assertEquals("Ioselev", persons.get(1).getLastName(), "Person lastName mismatch");
		 assertEquals("930-470-1384", persons.get(1).getPhone(), "Person phone number mismatch");
		 assertEquals("cioselev1@ehow.com", persons.get(1).getEmails().get(0), "Person email mismatch");
	}
	
	/**
	 * Tests for correct data parsing in Company.class
	 * @throws IOException
	 */
	@Test
	public void readCompaniesTest() throws IOException{
		
		 String testCsvPath = Paths.get("data/Companies.csv").toString();
		 
		 List<Company> companies = CSVReader.readCompanies(testCsvPath);
		 	 
		 assertNotNull(companies, "List should not be empty");
		 assertEquals(10, companies.size(), "Should contain exactly 10 companies");
		 
		 assertEquals("5119aca1-1b5b-4ce9-ba85-4a4ed392ddc3", companies.get(0).getCompanyUuid().toString(), "Company UUID Mismatch");
		 assertEquals("663dd1b7-926c-4a8c-86d6-3134173c0ce5", companies.get(0).getContactUuid().toString(), "Company contact UUID mismatch");
		 assertEquals("Voonix", companies.get(0).getName(), "Company name mismatch");
		 assertEquals("4 Grim Junction, Durham, NC, 27710", companies.get(0).getFullAddress(), "Company Full Address mismatch");
		 
		 assertEquals("536654c6-72d5-4133-8641-30c73571ccb6", companies.get(1).getCompanyUuid().toString(), "Company UUID Mismatch 2nd Object");
		 assertEquals("55a430da-ad06-426d-80b2-989e43196efe", companies.get(1).getContactUuid().toString(), "Company contact UUID mismatch");
		 assertEquals("Plajo", companies.get(1).getName(), "Company name mismatch");
		 assertEquals("13458 Bultman Park, Wichita, KS, 67230", companies.get(1).getFullAddress(), "Company Full Address mismatch");
	}
	
	/**
	 * Tests for correct data parsing in Company.class
	 * @throws IOException
	 */
	@Test
	public void readItemsTest() throws IOException{
		
		 String testCsvPath = Paths.get("data/Items.csv").toString();
		 
		 List<Item> items = CSVReader.readItems(testCsvPath);
		 	 
		 assertNotNull(items, "List (Check the path) should not be empty");
		 assertEquals(15, items.size(), "Should contain exactly 15 Items");
		 
		 //Testing Equipment Objects from Item list
		 
		 assertEquals("2dd5220e-6779-4fe7-81d2-b79dcbff3415", items.get(0).getUuid().toString(), "Item UUID Mismatch");
		 assertEquals("Dragline", items.get(0).getName(), "Item name mismatch");
		 assertTrue(items.get(0) instanceof Equipment, "First object is not an Equipment");
		 
		 Equipment equipment = (Equipment)items.get(0);
		 
		 assertEquals('E', equipment.getType(), "Equipment Type mismatch");
		 assertEquals("DX200b", equipment.getModel(), "Equipment model mismatch");
		 assertEquals(347125, equipment.getPrice(), "Equipment price mismatch");
		 
		 
		 //Testing Material objects from Item list
		 
		 assertEquals("ac47e0f6-5b5b-49fe-af54-a6625a6374a8", items.get(5).getUuid().toString(), "Material UUID Mismatch");
		 assertEquals("concrete", items.get(5).getName(), "Material name mismatch");
		 assertTrue(items.get(5) instanceof Material, "Fifth object is not a Material");
		 
		 Material material = (Material) items.get(5);
		 
		 assertEquals('M', material.getType(), "Material Type mismatch"); 
		 assertEquals("bag", material.getUnit(), "material model mismatch");
		 assertEquals(40, material.getCostPerUnit(), "Material price mismatch");
		 
		 //Testing Contract Objects from Item List
		 
		 assertEquals("38bebacb-a3ca-459e-a71f-8e5826a2f7fa", items.get(10).getUuid().toString(), "Contract UUID Mismatch");
		 assertEquals("foundation pour", items.get(10).getName(), "Contract name mismatch");
		 assertTrue(items.get(10) instanceof Contract, "Eleventh object is not a Material");
		 
		 Contract contract = (Contract) items.get(10);
		 
		 assertEquals('C', contract.getType(), "Material Type mismatch"); 
		 assertEquals("0673a09a-5cc1-4269-88f2-e665c2f3f33c", contract.getCompanyUuid().toString(), "Contract contact UUID mismatch");
		 
	}
}
