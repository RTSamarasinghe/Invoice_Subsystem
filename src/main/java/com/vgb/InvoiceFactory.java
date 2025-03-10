package com.vgb;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class InvoiceFactory {

	 private Map<UUID, Person> personLookup;
	
	 private final Map<UUID, Company> companyLookup;
	
	
	public InvoiceFactory(Map<UUID, Person> personLookup, Map<UUID, Company> companylookup) {
		this.personLookup = personLookup;
		this.companyLookup = companylookup;
	}



	public Invoice createInvoice(String[] parts) {
		
		
		if (parts.length < 4) {
			throw new IllegalArgumentException("Invalid CSV format, missing required fields");
		}
		
		String inv_uuidstr = parts[0].trim();
		UUID cus_uuidstr = UUID.fromString(parts[1].trim());
		UUID sales_uuidstr = UUID.fromString(parts[2].trim());
		if(inv_uuidstr.isEmpty()) {
			throw new IllegalArgumentException("Missing UUID in CSV: " + Arrays.toString(parts));
		}
		
		UUID invoiceUuid;
		Company customerUuid ;
		Person salesPersonUuid;
		
		invoiceUuid = UUID.fromString(inv_uuidstr);
		customerUuid = companyLookup.get(cus_uuidstr);
		salesPersonUuid = personLookup.get(sales_uuidstr);
		
			
		
		LocalDate date = LocalDate.parse(parts[3]);
		
		return new Invoice(invoiceUuid, customerUuid, salesPersonUuid , date);
	}
}
