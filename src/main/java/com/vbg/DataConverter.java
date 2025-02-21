package com.vbg;

import java.awt.ItemSelectable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.vbg.CSVReader;

/*
 * Outputs .json and .xml files parsed in as List objects.
 */
public class DataConverter {
	
/**
 * Converts Java Objects to JSON Objects and outputs it to a .json file	
 * @param outputpath	The location of the output file
 * @param entity The class to be converted
 */
	public static void convertToJson(String inputfile, String outputpath, Object entity) {
		
		String json = "";
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.create();
		
		try {
			
			if (entity == Person.class) {
				json = gson.toJson(CSVReader.readPersons(inputfile));
			}
			else if (entity == Company.class) {
				json = gson.toJson(CSVReader.readCompanies(inputfile));
			}
			else if (entity == Item.class) {
				json = gson.toJson(CSVReader.readItems(inputfile));
			}
			
			
			try (FileWriter file = new FileWriter(outputpath)) {
	            file.write(json);
	            System.out.println("JSON file written succesfully");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
		} catch (IOException e) {
			System.err.println("CSVReader error");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Converts Java Objects to XML Objects and outputs it to a .xml file	
	 * @param outputpath	The location of the output file
	 * @param entity The class to be converted
	 */
	
	public static void convertToXML(String inputFile, String outputpath, Object entity) {
		
		
		String XML = "";
		XStream xstream = new XStream();
		
	
		xstream.alias("person", Person.class);
		xstream.alias("Company", Company.class);
		xstream.alias("Material", Material.class);
		xstream.alias("Equipment", Equipment.class);
		xstream.alias("Contract", Contract.class);
		
		try {
			
				if (entity == Person.class) {
					XML = xstream.toXML(CSVReader.readPersons(inputFile));
				}
				else if (entity == Company.class) {
					XML = xstream.toXML(CSVReader.readCompanies(inputFile));
				}
				else if (entity == Item.class) {
					XML = xstream.toXML(CSVReader.readItems(inputFile));
				}
				
				try (FileWriter file = new FileWriter(outputpath)) {
		            file.write(XML);
		            System.out.println("XML file written succesfully");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
			} catch (IOException e) {
				System.err.println("CSVReader error");
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) throws IOException {
		
		convertToJson("data/Persons.csv", "output/Persons.json", Person.class);
		convertToJson("data/Items.csv","output/Items.json", Item.class);
		convertToJson("data/Companies.csv","output/Companies.json", Company.class);
		
		convertToXML("data/Persons.csv","output/Persons.xml", Person.class);
		convertToXML("data/Items.csv", "output/Items.xml", Item.class);
		convertToXML("data/Companies.csv", "output/Companies.xml", Company.class);
		
		

	}

}
