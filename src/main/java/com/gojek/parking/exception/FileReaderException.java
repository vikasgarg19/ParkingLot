package com.gojek.parking.exception;

/**
 * Exception class for FileReader operation.
 * 
 * @author Vikas Garg
 *
 */
public class FileReaderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileReaderException() {
		super();
	}
	
	public FileReaderException(String message) {
		super(message);
	}
	
	public FileReaderException(String message, Throwable exception) {
		super(message, exception);
	}
}