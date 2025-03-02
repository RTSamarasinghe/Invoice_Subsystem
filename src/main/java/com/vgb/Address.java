package com.vgb;
/*
 * Represent an Address Object in the system
 * contains details of 
 * 
 */
public class Address {
	
	
    private String street;
    private String city;
    private String state;
    private String zip;
    
    
    
    /*
     * @param street      The street address of the company
     * @param city        The city of the company's address
     * @param state       The state of the company's address
     * @param zip         The ZIP code of the company's address
     */
    
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
    
	 public String getFullAddress() {
	        return street + ", " + city + ", " + state + ", " + zip;
	    }

    

}
