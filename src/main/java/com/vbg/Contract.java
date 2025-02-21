package com.vbg;

import java.util.UUID;

/**
 * Represents a contract available in the system.
 */
public class Contract extends Item {
    private final UUID companyUuid;

    /**
     * Constructs a Contract item with the given attributes
     * 
     * @param uuid        The UUID of the contract
     * @param name        The name/description of the contract
     * @param companyUuid The UUID of the company that VGB subcontracts with
     */
   
    public Contract(UUID uuid, String name, UUID companyUuid) {
        super(uuid, name);
        this.companyUuid = companyUuid;
    }

    @Override
    public char getType() {
        return 'C';
    }

    
    public UUID getCompanyUuid() {
        return companyUuid;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "uuid=" + getUuid() +
                ", name='" + getName() + '\'' +
                ", companyUuid=" + companyUuid +
                '}';
    }
}