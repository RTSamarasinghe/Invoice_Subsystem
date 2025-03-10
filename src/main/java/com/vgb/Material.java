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

    public String getUnit() {
        return unit;
    }

    
    public double getCostPerUnit() {
        return costPerUnit;
    }
    
    public double getQuantity() {
    	return quantity;
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

	@Override
	public String toString() {
		return String.format("Material:%8s"
				+"\n---------------------"
				+ "\nCost per unit: %.2f" + " | " + "%-6s"
				+ "\nQuantity: %.2f"
				+ "\nTax: %.2f"
				+ "\nTotal: %.2f",super.getName(),getCostPerUnit(),getUnit(),getQuantity(),calculateTax(),calculateTotal());
		
	}

	

}

