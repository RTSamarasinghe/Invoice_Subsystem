package com.vgb;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class InvoiceFactory {

	
	public static Invoice createInvoice(String[] parts) {
		
		if (parts.length < 4) {
			throw new IllegalArgumentException("Invalid CSV format, missing required fields");
		}
		
		String inv_uuidstr = parts[0].trim();
		String cus_uuidstr = parts[1].trim();
		String sales_uuidstr = parts[2].trim();
		if(inv_uuidstr.isEmpty()) {
			throw new IllegalArgumentException("Missing UUID in CSV: " + Arrays.toString(parts));
		}
		
		UUID invoiceUuid;
		UUID customerUuid;
		UUID salesPersonUuid;
		try {
			invoiceUuid = UUID.fromString(inv_uuidstr);
			customerUuid = UUID.fromString(cus_uuidstr);
			salesPersonUuid = UUID.fromString(sales_uuidstr);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid UUID in Invoice");
		}
		
		LocalDate date = LocalDate.parse(parts[3]);
		
		return new Invoice(invoiceUuid, customerUuid, salesPersonUuid, date);
	}
}
