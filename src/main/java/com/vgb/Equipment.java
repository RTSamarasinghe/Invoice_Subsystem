package com.vgb;

import java.util.UUID;

/**
 * Represents equipment available in the system.
 */
public class Equipment extends Item implements Taxable {
    private final String model;
    private final double price;
    private final static double TAX_RATE = 0.0525;
    /**
     * Constructs an Equipment item with the given attributes
     * 
     * @param uuid  The UUID of the equipment
     * @param name  The name/description of the equipment
     * @param model The model number of the equipment
     * @param price The retail price of the equipment
     */
   
    public Equipment(UUID uuid, String name, String model, double price) {
        super(uuid, name);
        this.model = model;
        this.price = price;
    }

    
    public String getModel() {
        return model;
    }

    
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Equipment:" +
                "(" + getUuid() + ")" + '\n'+
                 getName() + '\n' +
                ",'" + model + '\'' +
                ",$ " + price + '\n';
    }
    
    @Override
	public double calculateTax() {		
		return this.price * TAX_RATE;
	}

	@Override
	public double calculateTotal() {
		return this.price + calculateTax();
	}

	
	
}
