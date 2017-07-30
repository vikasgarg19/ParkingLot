package com.gojek.parking.exception;

/**
 * Exception class for ParkingLot System.
 * 
 * @author Vikas Garg
 *
 */
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