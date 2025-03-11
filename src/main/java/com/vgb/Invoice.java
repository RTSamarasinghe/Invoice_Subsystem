package com.vgb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an Invoice Object
 */
public class Invoice  {
	
	    private UUID invoiceUUID;
	    private Company customerUUID;
	    private Person salespersonUUID;
	    private LocalDate invoiceDate;
	    private List<InvoiceItem> items;

	    public Invoice(UUID invoiceUUID, Company customerUUID, Person salespersonUUID, LocalDate invoiceDate) {
	        this.invoiceUUID = invoiceUUID;
	        this.customerUUID = customerUUID;
	        this.salespersonUUID = salespersonUUID;
	        this.invoiceDate = invoiceDate;
	        this.items = new ArrayList<>();
	    }

	    public void addItem(InvoiceItem item) {
	        items.add(item);
	    }

	    public double calculateSubtotal() {
	        return items.stream().mapToDouble(InvoiceItem::getSubtotal).sum();
	    }

	    public double calculateTaxTotal() {
	        return items.stream().mapToDouble(InvoiceItem::getTax).sum();
	    }

	    public double calculateGrandTotal() {
	        return calculateSubtotal() + calculateTaxTotal();
	    }

		public UUID getInvoiceUUID() {
			return invoiceUUID;
		}

		public Company getCustomerUUID() {
			return customerUUID;
		}

		public Person getSalespersonUUID() {
			return salespersonUUID;
		}

		public LocalDate getInvoiceDate() {
			return invoiceDate;
		}

		public List<InvoiceItem> getItems() {
			return items;
		}
//TODO: Edit to match example output
		//TODO: fix the damn bug
		
		@Override
		public String toString() {
		    StringBuilder sb = new StringBuilder();
		    
		    sb.append(String.format("Invoice UUID: %s\n", invoiceUUID.toString()));
		    sb.append(String.format("Customer UUID: %s\n", customerUUID.toString()));
		    sb.append(String.format("Salesperson UUID: %s\n", salespersonUUID.toString()));
		    sb.append(String.format("Invoice Date: %s\n", invoiceDate.toString()));
		    sb.append("\nItems:\n");

		    for (InvoiceItem item : items) {
		        sb.append(item.toString()).append("\n");
		    }

		    sb.append(String.format("\nSubtotal: %.2f\n", calculateSubtotal()));
		    sb.append(String.format("Tax Total: %.2f\n",calculateTaxTotal()));
		    sb.append(String.format("Grand Total: %.2f\n", calculateGrandTotal()));
		    
		    sb.append(String.format("Grand Total: %.2f\n", calculateGrandTotal()));
		    sb.append(String.format("Company name: %s" , getCustomerUUID().getName()));
		    
		    return sb.toString();
		}
		
	}

	

