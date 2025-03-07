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
	private UUID customerUUID;
	private UUID salespersonUUID;
	private LocalDate invoiceDate;
	
	

	public Invoice(UUID invoiceUUID, UUID customerUUID, UUID salespersonUUID, LocalDate invoiceDate) {
		this.invoiceUUID = invoiceUUID;
		this.customerUUID = customerUUID;
		this.salespersonUUID = salespersonUUID;
		this.invoiceDate = invoiceDate;
	}


	public UUID getInvoiceUUID() {
		return this.invoiceUUID;
	}


	public UUID getCustomerUUID() {
		return this.customerUUID;
	}


	public UUID getSalespersonUUID() {
		return this.salespersonUUID;
	}


	public LocalDate getInvoiceDate() {
		return this.invoiceDate;
	}

	
	
	
}
