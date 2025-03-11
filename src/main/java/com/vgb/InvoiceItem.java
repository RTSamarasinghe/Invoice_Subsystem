package com.vgb;

import java.util.UUID;

/**
 * Represents an item in an invoice.
 */
public class InvoiceItem {
    private Object item;
    //TODO: make Agreement and Item generic enough to be used in the methods
    private UUID invoiceUUID;

    public InvoiceItem(UUID invoiceUUID,Object item) {
    	this.invoiceUUID = invoiceUUID;
        this.item = item;
    }

    public double getSubtotal() {
        if (item instanceof Equipment) {
            return ((Equipment) item).getPrice();
        } else if (item instanceof Lease) {
            return ((Lease) item).calculateAgreement();
        } else if (item instanceof Rental) {
            return ((Rental) item).calculateAgreement();
        } else if (item instanceof Material) {
            return ((Material) item).calculateTotal();
        } else if (item instanceof Contract) {
            return ((Contract) item).calculateTotal();
        }
        return 0;
    }

    public double getTax() {
        if (item instanceof Equipment) {
            return ((Equipment) item).calculateTax();
        } else if (item instanceof Lease) {
            return ((Lease) item).calculateTax();
        } else if (item instanceof Rental) {
            return ((Rental) item).calculateTax();
        } else if (item instanceof Material) {
            return ((Material) item).calculateTax();
        }
        return 0;
    }
    
    
}
