package com.vgb;

public abstract class Agreement {
	
	private Equipment equipment;

	/*
	 * Calculates the current Lease or Rental on Equipment
	 */
	public abstract double calculateAgreement();
	
	  public Agreement(Equipment equipment) {
	        this.equipment = equipment;
	    }

}
