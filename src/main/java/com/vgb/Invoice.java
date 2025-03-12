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

		public Company getCustomer() {
			return customerUUID;
		}

		public Person getSalesPerson() {
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
		    return String.format(
		    		"\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="
		    		+ '\n'
		    		+"Invoice: %36s"
		    		+"\nDate %14s"
		    		+ '\n'
		    		+"\nCustomer: \n%s (%36s)"
		    		+ "\n %s (%36s) \n%s \n"
		    		+ "\nSales Person:"
		    		+ "\n %s (%36s) \n %s \n",
		    		
		    		
		    		
		    		
		    		this.getInvoiceUUID(), this.getInvoiceDate(), this.getCustomer().getName(),
		    		this.getCustomer().getCompanyUuid(),
		    		getCustomer().getContactPerson().getFullName(), this.getCustomer().getContactPerson().getUuid(),
		    		this.getCustomer().getContactPerson().getEmails(),
		    		this.getSalesPerson().getFullName(), this.getSalesPerson().getUuid(),
		    		this.getSalesPerson().getEmails());
		}
		
	}

	

