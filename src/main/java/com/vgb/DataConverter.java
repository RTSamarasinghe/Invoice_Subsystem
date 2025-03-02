package com.vgb;

import java.awt.ItemSelectable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.vgb.CSVReader;

/*
 * Outputs .json and .xml files parsed in as List objects.
 */
public class DataConverter {
	
	/**
	 * Converts Java Objects to JSON Objects and outputs it to a .json file	
	 * @param outputpath	The location of the output file
	 * @param entity The class to be converted
	 */
	public static String convertToJson(String inputfile, Class<?> entity) {
		
		String json = "";
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.create();
		
		try {
			
			if (entity == Person.class) {
				json = gson.toJson(CSVReader.readPersons(inputfile));
			}
			else if (entity == Company.class) {
				json = gson.toJson(CSVReader.readCompanies(inputfile,CSVReader.readPersons("data/Persons.csv")));
			}
			else if (entity == Item.class) {
				json = gson.toJson(CSVReader.readItems(inputfile,CSVReader.readCompanies(
						"data/Companies.csv", 
						CSVReader.readPersons("data/Persons.csv"))));
			}
			
		} catch (IOException e) {
			System.err.println("CSVReader error");
			e.printStackTrace();
		}
		
		return json;
		
	}
	
	public static void writeToJSON(String inputPath, String outputpath, Class<?> entity) {
		try (FileWriter file = new FileWriter(outputpath)) {
            file.write(convertToJson(inputPath, entity));
            System.out.println("JSON file for " + entity.getSimpleName() + " written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * Converts Java Objects to XML Objects and outputs it to a .xml file	
	 * @param outputpath	The location of the output file
	 * @param entity The class to be converted
	 */
	
	public static String convertToXML(String inputFile,  Class<?> entity)  {
		
		
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
					XML = xstream.toXML(CSVReader.readCompanies(inputFile, CSVReader.readPersons("data/Persons.csv")));
				}
				else if (entity == Item.class) {
					XML = xstream.toXML(CSVReader.readItems(inputFile, CSVReader.readCompanies("data/Companies.csv", 
							CSVReader.readPersons("data/Persons.csv"))));
				}
				
			} catch (IOException e) {
				System.err.println("CSVReader error");
				e.printStackTrace();
			}
		
		return XML;
	}
	
	public static void writeToXML(String inputPath, String outputpath, Class<?> entity) {
		try (FileWriter file = new FileWriter(outputpath)) {
            file.write(convertToXML(inputPath, entity));
            System.out.println("XML file for " + entity.getSimpleName() + " written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) throws IOException {
		
		writeToJSON("data/Persons.csv", "data/Persons_output.json", Person.class);
		writeToJSON("data/Items.csv","data/Items_output.json", Item.class);
		writeToJSON("data/Companies.csv","data/Companies_output.json", Company.class);
		
		writeToXML("data/Persons.csv","data/Persons_output.xml", Person.class);
		writeToXML("data/Items.csv", "data/Items_output.xml", Item.class);
		writeToXML("data/Companies.csv", "data/Companies_output.xml", Company.class);
		
		
		
		}

}
