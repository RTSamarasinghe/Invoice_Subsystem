package com.vgb;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Lease extends Agreement {

	Equipment equipment;
	LocalDate startDate;
	LocalDate endDate;
	
	
	public Lease(Equipment equipment, LocalDate startDate, LocalDate endDate) {
		super(equipment);
		this.startDate = startDate;
		this.endDate = endDate;
	}


	@Override
	public double calculateAgreement() {
		long days = ChronoUnit.DAYS.between(startDate, endDate) + 1; 
        double years = days / 365.0;
        return (years / 5) * equipment.getPrice() * 1.5;
	}
	
	
	
	
}
