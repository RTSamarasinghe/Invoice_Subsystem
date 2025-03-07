package com.vgb;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Lease extends Agreement implements Taxable {

	Equipment equipment;
	LocalDate startDate;
	LocalDate endDate;
	
	
	private double tax;
	private static final double FLAT_TAX = 1500.00;
	
	public Lease(Equipment equipment, LocalDate startDate, LocalDate endDate) {
		this.equipment = equipment;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	@Override
	public double calculateAgreement() {
		long days = ChronoUnit.DAYS.between(startDate, endDate) + 1; 
        double years = days / 365.0;
        return Math.round((years / 5) * equipment.getPrice() * 1.5);
	}


	@Override
	public double calculateTax() {
		if (calculateAgreement() > 12500.00) {
			tax = FLAT_TAX;
		}
		return tax;
	}


	public double getTax() {
		return tax;
	}


	@Override
	public double calculateTotal() {
		return calculateAgreement() + calculateTax();
	}
	
	@Override
	public String toString() {
		return String.format("%12f, %12f",calculateTax(), calculateAgreement() ) ;
		
	}
	
	
	
}
