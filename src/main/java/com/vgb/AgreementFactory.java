package com.vgb;

/*
 * 
 * Utility for loose Coupling Lease and Rental objects
 * 
 */
public abstract class AgreementFactory {

	public abstract Agreement createAgreement(Equipment equipment, Object startperiod, Object endperiod );
	
}
