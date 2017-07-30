package com.gojek.parking.exception;

public class ParkingLotException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParkingLotException() {
		super();
	}
	
	public ParkingLotException(String message) {
		super(message);
	}
	
	public ParkingLotException(String message, Throwable exception) {
		super(message, exception);
	}
}