package com.vgb;

import java.time.LocalDate;

/*
 * 
 * Creates Lease Objects
 * 
 */
public class LeaseFactory extends AgreementFactory{

	@Override
	public Agreement createAgreement(Equipment equipment, Object startperiod, Object endperiod) {
		
		return new Lease(equipment, (LocalDate)startperiod,(LocalDate) endperiod);
	}

}
