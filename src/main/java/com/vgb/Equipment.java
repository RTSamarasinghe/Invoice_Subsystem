package com.vgb;

import java.util.UUID;

/**
 * Represents equipment available in the system.
 */
public class Equipment extends Item {
    private final String model;
    private final double price;

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

    @Override
    public char getType() {
        return 'E';
    }

    
    public String getModel() {
        return model;
    }

    
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "uuid=" + getUuid() +
                ", name='" + getName() + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
