package com.vgb;

import org.junit.Test;

import com.vgb.CSVReader;
import com.vgb.Company;
import com.vgb.Contract;
import com.vgb.Equipment;
import com.vgb.Item;
import com.vgb.Material;
import com.vgb.Person;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a suite of tests for the various {@link #CSVReader} derived classes.
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
		 
		 Map<UUID,Person> persons = CSVReader.readPersons(testCsvPath);
		 	 
		 assertNotNull(persons, "List should not be empty");
		 assertEquals(10, persons.size(), "Should contain exactly 10 persons");
		 
		 UUID key1 = UUID.fromString("db0c8f6e-6754-43bd-957a-56e25db4bf58");
		 
		 assertEquals("db0c8f6e-6754-43bd-957a-56e25db4bf58", persons.get(key1).getUuid().toString(), "Person UUID Mismatch");
		 assertEquals("Baillie", persons.get(key1).getFirstName(), "Person FirstName mismatch");
		 assertEquals("Doyle", persons.get(key1).getLastName(), "Person laastName mismatch");
		 assertEquals("206-405-6942", persons.get(key1).getPhone(), "Person Phone mismatch");
		 assertEquals("bdoyle0@google.it", persons.get(key1).getEmails().get(0), "Person Email mismatch");
		 
		 UUID key2 = UUID.fromString("e6fd450d-9c21-44c3-8c5a-e788243768a5");
		 
		 assertEquals("e6fd450d-9c21-44c3-8c5a-e788243768a5", persons.get(key2).getUuid().toString(), "Person UUID Mismatch");
		 assertEquals("Baudoin", persons.get(key2).getFirstName(), "Person firstName mismatch");
		 assertEquals("Leere", persons.get(key2).getLastName(), "Person lastName mismatch");
		 assertEquals("237-269-2718", persons.get(key2).getPhone(), "Person phone number mismatch");
		 assertEquals("bleere9@privacy.gov.au", persons.get(key2).getEmails().get(0), "Person email mismatch");
	}
	
	/**
	 * Tests for correct data parsing in Company.class
	 * @throws IOException
	 */
	@Test
	public void readCompaniesTest() throws IOException{
		
		 String testCsvPath = Paths.get("data/Companies.csv").toString();
		 
		 Map<UUID, Company> companies = CSVReader.readCompanies(testCsvPath, CSVReader.readPersons("data/Persons.csv"));
		 	 
		 assertNotNull(companies, "List should not be empty");
		 assertEquals(10, companies.size(), "Should contain exactly 10 companies");
		 
		 UUID key1 = UUID.fromString("11efd8b7-3ddf-4884-bda4-91b9b8a6045d");
		 
		 assertEquals("11efd8b7-3ddf-4884-bda4-91b9b8a6045d", companies.get(key1).getCompanyUuid().toString(), "Company UUID Mismatch");
		 assertEquals("db0c8f6e-6754-43bd-957a-56e25db4bf58", companies.get(key1).getContactUuid().toString(), "Company contact UUID mismatch");
		 assertEquals("Thoughtworks", companies.get(key1).getName(), "Company name mismatch");
		 assertEquals("38 Ridge Oak Road, Buffalo, NY, 14269", companies.get(key1).getAddress().getFullAddress(), "Company Full Address mismatch");
		 
		 UUID key2 = UUID.fromString("965c71ca-77c3-49a8-9f0c-5aa61630e417");

		 
		 assertEquals("965c71ca-77c3-49a8-9f0c-5aa61630e417", companies.get(key2).getCompanyUuid().toString(), "Company UUID Mismatch 2nd Object");
		 assertEquals("e6fd450d-9c21-44c3-8c5a-e788243768a5", companies.get(key2).getContactUuid().toString(), "Company contact UUID mismatch");
		 assertEquals("Bluejam", companies.get(key2).getName(), "Company name mismatch");
		 assertEquals("6 Forest Dale Terrace, Akron, OH, 44329", companies.get(key2).getAddress().getFullAddress(), "Company Full Address mismatch");
	}
	
	/**
	 * Tests for correct data parsing in Company.class
	 * @throws IOException
	 */
	@Test
	public void readItemsTest() throws IOException{
		
		 String testCsvPath = Paths.get("data/Items.csv").toString();
		 
		 Map<UUID, Item> items = CSVReader.readItems(testCsvPath, CSVReader.readCompanies("data/Companies.csv"
				 , CSVReader.readPersons("data/Persons.csv")));
		 	 
		 assertNotNull(items, "List (Check the path) should not be empty");
		 assertEquals(15, items.size(), "Should contain exactly 15 Items");
		 
		 //Testing Equipment Objects from Item list
		 
		 UUID key1 = UUID.fromString("146475e4-241a-4ac4-a571-51ec95794624");

		 
		 assertEquals("146475e4-241a-4ac4-a571-51ec95794624", items.get(key1).getUuid().toString(), "Item UUID Mismatch");
		 assertEquals("Excavator", items.get(key1).getName(), "Item name mismatch");
		 assertTrue(items.get(key1) instanceof Equipment, "First object is not an Equipment");
		 
		 Equipment equipment = (Equipment)items.get(key1);
		 
		
		 assertEquals("EX300Z", equipment.getModel(), "Equipment model mismatch");
		 assertEquals(365000, equipment.getPrice(), "Equipment price mismatch");
		 
		 
		 //Testing Material objects from Item list
		 
		 UUID key2 = UUID.fromString("56a775ee-7521-4c8a-9f0c-175dea919691");
		 
		 assertEquals("56a775ee-7521-4c8a-9f0c-175dea919691", items.get(key2).getUuid().toString(), "Material UUID Mismatch");
		 assertEquals("cement", items.get(key2).getName(), "Material name mismatch");
		 assertTrue(items.get(key2) instanceof Material, items.get(key2).getName() + "object is not a Material");
		 
		 Material material = (Material) items.get(key2);
		 
		
		 assertEquals("bag", material.getUnit(), "material model mismatch");
		 assertEquals(42, material.getCostPerUnit(), "Material price mismatch");
		 
		 //Testing Contract Objects from Item List
		 
		 UUID key3 = UUID.fromString("1672210c-f7c5-4642-87c4-0dd824693d07");
		 
		 assertEquals("1672210c-f7c5-4642-87c4-0dd824693d07", items.get(key3).getUuid().toString(), "Contract UUID Mismatch");
		 assertEquals("wall construction", items.get(key3).getName(), "Contract name mismatch");
		 assertTrue(items.get(key3) instanceof Contract, "Eleventh object is not a Material");
		 
		 Contract contract = (Contract) items.get(key3);
		 
		 
		 assertEquals("11efd8b7-3ddf-4884-bda4-91b9b8a6045d", contract.getCompanyUuid().toString(), "Contract contact UUID mismatch");
		 
	}
}
