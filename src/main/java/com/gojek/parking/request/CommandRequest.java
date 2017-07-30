package com.gojek.parking.request;

import com.gojek.parking.domain.Car;
import com.gojek.parking.enums.Command;

/**
 * CommandRequest object which contains all the information related to command.
 * 
 * @author Vikas Garg
 */
public class CommandRequest {

	// Command Object
	private Command command;
	
	// Car object derived from input
	private Car car = null;
	
	// Data Input which is for actual execution of command.
	private String dataInput = null;
	
	// Line input which is coming from input
	private String lineInput = null;
	
	/**
	 * To get the Car object
	 * 
	 * @return
	 */
	public Car getCar() {
		return car;
	}
	
	/**
	 * To set the Car
	 * 
	 * @param car
	 */
	public void setCar(Car car) {
		this.car = car;
	}
	
	/**
	 * To get Command
	 * 
	 * @return
	 */
	public Command getCommand() {
		return command;
	}
	
	/**
	 * To set Command
	 * 
	 * @param command
	 */
	public void setCommand(Command command) {
		this.command = command;
	}
	
	/**
	 * To get Data Input
	 * 
	 * @return
	 */
	public String getDataInput() {
		return dataInput;
	}
	
	/**
	 * To set Data Input
	 * 
	 * @param dataInput
	 */
	public void setDataInput(String dataInput) {
		this.dataInput = dataInput;
	}
	
	/**
	 * To get Line Input
	 * 
	 * @return
	 */
	public String getLineInput() {
		return lineInput;
	}
	
	/**
	 * To set LineInput
	 * 
	 * @param lineInput
	 */
	public void setLineInput(String lineInput) {
		this.lineInput = lineInput;
	}
}