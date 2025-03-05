package com.vgb;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Rental extends Agreement {

	Equipment equipment;
	private LocalDateTime startTime;
    private LocalDateTime endTime;
	
	public Rental(Equipment equipment, LocalDateTime startTime, LocalDateTime endTime) {
		super(equipment);
		this.startTime = startTime;
		this.endTime = endTime;
		
	}

	@Override
	public double calculateAgreement() {
		long hours = ChronoUnit.HOURS.between(startTime, endTime);
		return (equipment.getPrice() * 0.001) * hours;
	}

}
