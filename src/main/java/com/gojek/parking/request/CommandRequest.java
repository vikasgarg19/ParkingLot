package com.gojek.parking.request;

import com.gojek.parking.domain.Car;
import com.gojek.parking.enums.Command;

public class CommandRequest {

	private Command command;
	private Car car = null;
	private String dataInput = null;
	private String lineInput = null;
	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public String getDataInput() {
		return dataInput;
	}
	public void setDataInput(String dataInput) {
		this.dataInput = dataInput;
	}
	public String getLineInput() {
		return lineInput;
	}
	public void setLineInput(String lineInput) {
		this.lineInput = lineInput;
	}
}