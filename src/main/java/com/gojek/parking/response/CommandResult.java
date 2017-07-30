package com.gojek.parking.response;

/**
 * Result object of command execution.
 * 
 * @author Vikas Garg
 *
 */
public class CommandResult {

	// To indicate the command result is success or not
	private boolean isSuccess = true;
	
	// To indicate the message of command execution
	private String message = null;

	/**
	 * To get the result of the command execution
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * To set the result of command execution.
	 * 
	 * @param isSuccess
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * To get the command execution message
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * To set the command execution message.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}