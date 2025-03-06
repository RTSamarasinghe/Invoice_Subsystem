package com.vgb;

import java.time.LocalDateTime;

/*
 * Creates rental objects
 */
public class RentalFactory extends AgreementFactory{

	@Override
	public Agreement createAgreement(Equipment equipment, Object startperiod, Object endperiod) {
		return new Rental(equipment,  (LocalDateTime) startperiod, (LocalDateTime) endperiod);
	}

}
