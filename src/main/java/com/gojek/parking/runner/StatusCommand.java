package com.gojek.parking.runner;

import org.apache.log4j.Logger;

import com.gojek.parking.domain.Car;
import com.gojek.parking.domain.ParkingLot;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * This class is for handling various cases of "STATUS" command.
 * 
 * @author Vikas Garg
 *
 */
public class StatusCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(StatusCommand.class);
	
	public StatusCommand() {
		super.currentCommand = Command.STATUS;
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
		
		StringBuilder message = new StringBuilder("");
		
		ParkingLot parkingLot = ParkingLot.getParkingLot();
		if (parkingLot != null) {
			Car[] cars = parkingLot.getParkingSlots();
			if (cars == null || cars.length <= 0) {
				
			} else {
				message.append("Slot No.").append("\t").append("Registration No").append("\t").append("Colour");
				message.append("\n");
				for (int count = 0; count < cars.length; count++) {
					if (cars[count] != null) {
						message.append(count + 1).append("\t").append(cars[count].getRegistrationNumber()).append("\t")
							.append(cars[count].getColor().name());
					}
					message.append("\n");
				}
			}
		}
		// Result Preparation
		commandResult.setSuccess(true);
		commandResult.setMessage(message.toString());
		logger.trace("Exit execute");
		return commandResult;
	}

	/**
	 * To validate the command which has been requested by the client. This method will validate all the inputs of 
	 * command SLOT_NUMBERS_FOR_REGISTRATION_NUMBER
	 * 
	 * @param commandRequest :
	 * 		CommandRequest object which contains message.
	 * 
	 * @throws ValidationException :
	 * 		If there is failure in command validation
	 */
	@Override
	public void validateCommand(CommandRequest commandRequest) throws ValidationException {
		if (commandRequest.getLineInput().length() < (currentCommand.getKey().length())) {
			throw new ValidationException("Invalid Input");
		}
		String[] inputs = commandRequest.getLineInput().split(" ");
		if (inputs.length < 1) {
			throw new ValidationException("");
		}
		commandRequest.setCommand(currentCommand); 
	}
}