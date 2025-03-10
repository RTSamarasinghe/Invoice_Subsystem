package com.vgb;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Rental extends Agreement implements Taxable {

	Equipment equipment;
	private LocalDateTime startTime;
    private LocalDateTime endTime;
	
    
    private static final double TAX = 0.0438;
	public Rental(Equipment equipment, LocalDateTime startTime, LocalDateTime endTime) {
		this.equipment = equipment;
		this.startTime = startTime;
		this.endTime = endTime;
		
	}
	
	

	@Override
	public double calculateAgreement() {
		long hours = ChronoUnit.HOURS.between(startTime, endTime);
		return Math.round((equipment.getPrice() * 0.001) * hours);
	}

	@Override
	public double calculateTax() {
		return calculateAgreement() * TAX;
	}

	@Override
	public double calculateTotal() {
		return Math.round(calculateAgreement() + calculateTax());
	}

	@Override
	public String toString() {
		return String.format("Equipment:%-8s"
				+ "\nRental:"
				+"\n---------------------"
				+ "\nTax: %.2f"
				+ "\nAgreement: %.2f"
				+ "\nTotal: %.2f",equipment.getName(),calculateTax(), calculateAgreement(), calculateTotal());
		
	}
	
}
