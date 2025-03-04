package com.vgb;

import java.time.LocalDate;

public interface Transaction {

	/**
	 * Calculates the time Period for a lease or Rent
	 * @return
	 */
	LocalDate getPeriod(LocalDate days);
	
	/**
	 * returns the total market price for the Item
	 * @return
	 */
	double calculateTotal();
	
}
