package com.vgb;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a contract available in the system.
 */
public class Contract extends Item{
    private final Company companyUuid;
    private double price;

    /**
     * Constructs a Contract item with the given attributes
     * 
     * @param uuid        The UUID of the contract
     * @param name        The name/description of the contract
     * @param companyUuid The UUID of the company that VGB subcontracts with
     */
   
    public Contract(UUID uuid, String name, Company companyUuid) {
        super(uuid, name);
        this.companyUuid = companyUuid;
    }
    
    public Contract(UUID uuid, String name, Company companyUuid, double price) {
        super(uuid, name);
        this.companyUuid = companyUuid;
        this.price = price;
    }

    @Override
    public Object getType() {
        return this.getClass();
    }

    
    public UUID getCompanyUuid() {
        return companyUuid.getCompanyUuid();
    }

    @Override
    public String toString() {
        return "Contract{" +
                "uuid=" + getUuid() +
                ", name='" + getName() + '\'' +
                ", companyUuid=" + companyUuid +
                '}';
    }

	@Override
	public double calculateTotal() {
		return price;
	}


}