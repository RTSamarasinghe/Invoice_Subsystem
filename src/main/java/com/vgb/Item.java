package com.vgb;

import java.util.UUID;

/**
 * Represents an abstract item in the system.
 * Base class for equipment, materials, and contracts.
 */
public abstract class Item {
    private final UUID uuid;
    private final String name;
    
    /**
     * Constructs an Item with the given attributes
     * 
     * @param uuid The UUID of the item
     * @param name The name/description of the item
     */
   
    protected Item(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
    
    
    public UUID getUuid() {
        return uuid;
    }
    
    
    public String getName() {
        return name;
    }
    
   
    public abstract char getType();
    
    public abstract double calculateTotal();
    
    @Override
    public String toString() {
        return "Item: " + '\n' +
                "(" + uuid + ")" +
                ",'" + name + '\n' +
                ", " + getType();
    	}
}