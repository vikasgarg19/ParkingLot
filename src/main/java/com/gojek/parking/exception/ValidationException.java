package com.gojek.parking.exception;

/**
 * Exception class for various input validation in ParkingLot system.
 * 
 * @author Vikas Garg
 *
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super();
	}
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(String message, Throwable exception) {
		super(message, exception);
	}
}