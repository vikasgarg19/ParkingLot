package com.gojek.parking.domain;

import com.gojek.parking.enums.Color;

/**
 * 
 * 
 * @author Vikas Garg
 */
public class Car {

	private String registrationNumber = null;
	
	private Color color = null;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [registrationNumber=" + registrationNumber + ", color="
			+ color + ", getRegistrationNumber()="
				+ getRegistrationNumber() + ", getColor()=" + getColor() + "]";
	}
}