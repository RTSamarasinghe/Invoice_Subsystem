package com.vgb;

import java.time.LocalDate;

/**
 * Contains all the methods for calculating rental
 * Used by Equipment
 */
public class Rental implements Transaction{

	LocalDate hours;
	double basePrice;
	double taxRate;
	
	
	
	public Rental(LocalDate hours, double basePrice, double taxRate) {
		this.hours = hours;
		this.basePrice = basePrice;
		this.taxRate = taxRate;
	}
	
	public LocalDate getHours() {
		return hours;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public double getTaxRate() {
		return taxRate;
	}

	@Override
	public LocalDate getPeriod(LocalDate days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
