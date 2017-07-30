package com.gojek.parking.domain;

import com.gojek.parking.enums.Colour;

/**
 * To maintain the Car object which will be used in ParkingLot System.
 * 
 * @author Vikas Garg
 */
public class Car {

	//Registration Number of Car
	private String registrationNumber = null;
	
	// Colour of Cars
	private Colour colour = null;

	/**
	 * Get Registration Number
	 * 
	 * @return
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * To set Registration Number
	 * 
	 * @param registrationNumber
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * String representation of Car
	 */
	@Override
	public String toString() {
		return "Car [registrationNumber=" + registrationNumber + ", color="
			+ colour + ", getRegistrationNumber()="
				+ getRegistrationNumber() + ", getColor()=" + getColour() + "]";
	}

	/**
	 * To get Colour of Car
	 * 
	 * @return
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * To set Colour of Car
	 * @param colour
	 */
	public void setColour(Colour colour) {
		this.colour = colour;
	}
}