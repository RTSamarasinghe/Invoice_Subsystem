package com.vgb;

import java.util.UUID;


/**
 * Represents material available in the system.
 */
public class Material extends Item implements Taxable {
    private final String unit;
    private final double costPerUnit;
    private double quantity;
    
    private final static double TAX_RATE = 0.0715;

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

    public Material(UUID uuid, String name, String unit, double costPerUnit,double quantity) {
        super(uuid, name);
        this.unit = unit;
        this.costPerUnit = costPerUnit;
        this.quantity = quantity;
    }
    
    @Override
    public Object getType() {
        return this.getClass();
    }

    public String getUnit() {
        return unit;
    }

    
    public double getCostPerUnit() {
        return costPerUnit;
    }

    @Override
    public String toString() {
        return "Material: \n" +
        		"(" + getUuid() + ")" +       
                ", " + getName() + '\n' +
                ", Units: '" + unit + '\n' +
                ", Cost: " + costPerUnit;
    }

	@Override
	public double calculateTotal() {
		double price = this.costPerUnit * this.quantity;
		return price;
	}

	@Override
	public double calculateTax() {
		double tax = calculateTotal() * TAX_RATE;
		return tax;
	}


}
