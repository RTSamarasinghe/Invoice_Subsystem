package com.vgb;

import java.time.LocalDate;

/*
 * Contains methods to calculate Lease
 * Used by Equipment Class
 * 
 */

public class Lease implements Transaction{
	
	LocalDate days;
	double basePrice;
	double markUpPrice;
	
	public Lease(LocalDate days, double basePrice, double markUpPrice) {
		this.days = days;
		this.basePrice = basePrice;
		this.markUpPrice = markUpPrice;
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
	
	public LocalDate getDays() {
		return days;
	}



	public double getBasePrice() {
		return basePrice;
	}



	public double getMarkUpPrice() {
		return markUpPrice;
	}
	

}
