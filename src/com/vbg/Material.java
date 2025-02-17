package com.vbg;

import java.util.UUID;


/**
 * Represents material available in the system.
 */
public class Material extends Item {
    private final String unit;
    private final double costPerUnit;

    /**
     * Constructs a Material item with the given attributes
     * 
     * @param uuid        The UUID of the material
     * @param name        The name/description of the material
     * @param unit        The unit of measurement for the material
     * @param costPerUnit The cost per unit of the material
     */
   
    public Material(UUID uuid, String name, String unit, double costPerUnit) {
        super(uuid, name);
        this.unit = unit;
        this.costPerUnit = costPerUnit;
    }

    @Override
    public char getType() {
        return 'M';
    }

    public String getUnit() {
        return unit;
    }

    
    public double getCostPerUnit() {
        return costPerUnit;
    }

    @Override
    public String toString() {
        return "Material{" +
                "uuid=" + getUuid() +
                ", name='" + getName() + '\'' +
                ", unit='" + unit + '\'' +
                ", costPerUnit=" + costPerUnit +
                '}';
    }
}
