package com.gojek.parking.command;

import org.apache.log4j.Logger;

import com.gojek.parking.domain.Car;
import com.gojek.parking.domain.ParkingLot;
import com.gojek.parking.domain.ParkingTicket;
import com.gojek.parking.enums.Colour;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * This class is for handling various cases of "PARK" command.
 * 
 * @author Vikas Garg
 *
 */
public class ParkCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(ParkCommand.class);
	
	public ParkCommand() {
		super.currentCommand = Command.PARK;
	}
	
	/**
	 * To validate the command which has been requested by the client. This method will validate all the inputs of 
	 * command PARK
	 * 
	 * @param commandRequest :
	 * 		CommandRequest object which contains message.
	 * 
	 * @throws ValidationException :
	 * 		If there is failure in command validation
	 */
	@Override
	public void validateCommand(CommandRequest commandRequest) throws ValidationException {
		logger.trace("Enter validateCommand");
		if (commandRequest.getLineInput().length() < (currentCommand.getKey().length() + 2)) {
			throw new ValidationException("Invalid Input");
		}
		
		String[] inputs = commandRequest.getLineInput().split(" ");
		if (inputs.length < 3) {
			throw new ValidationException("Invalid Command Format for : " + currentCommand.name() + 
				commandRequest.getLineInput());
		}

		// park KA-01-HH-1234 White
		commandRequest.setCommand(currentCommand); 
		Car car = new Car();
		car.setRegistrationNumber(inputs[1]);
		car.setColour(Colour.getColourByName(inputs[2]));
		commandRequest.setCar(car);
		logger.trace("Exit validateCommand");
	}

	/**
	 * To execute the command and provide the result.
	 * 
	 * @param request :
	 * 		CommandRequest object which contains information related to command
	 * 
	 * @return :
	 * 		CommandResult which contains information about result, status of the command execution and error message 
	 * 		if any. 
	 */
	@Override
	public CommandResult execute(CommandRequest request) {
		logger.trace("Enter execute");
		CommandResult commandResult = new CommandResult();
		
		ParkingLot parkingLot = ParkingLot.getParkingLot();
		ParkingTicket parkingTicket = null;
		if (parkingLot != null) {
			parkingTicket = parkingLot.parkCar(request.getCar());
		}
		
		// Result Preparation
		commandResult.setSuccess(true);
		StringBuilder message = new StringBuilder("");
		if (parkingTicket != null) {
			message.append("Allocated slot number: ")
			.append(parkingTicket.getSlotNumber());
		} else {
			message.append("Sorry, parking lot is full");
		}
		commandResult.setMessage(message.toString());
		logger.trace("Exit execute");
		return commandResult;
	}
}