package com.gojek.parking.command;

import org.apache.log4j.Logger;

import com.gojek.parking.domain.ParkingLot;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * This class is for handling various cases of "CREATE_PARKING_LOT" command.
 * 
 * @author Vikas Garg
 *
 */
public class CreateParkingLotCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(CreateParkingLotCommand.class);
	
	public CreateParkingLotCommand() {
		super.currentCommand = Command.CREATE_PARKING_LOT;
	}
	
	/**
	 * To validate the command which has been requested by the client. This method will validate all the inputs of 
	 * command CREATE_PARKING_LOT
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
		if (inputs.length < 2) {
			throw new ValidationException("Insufficient Input Command for : " + currentCommand.name() + 
				commandRequest.getLineInput());
		}
		
		Integer parkingLot = null;
		try {
			parkingLot = Integer.parseInt(inputs[1]);
		} catch (NumberFormatException e) {
			throw new ValidationException("Invalid ParkingLot Initialization ", e);
		}
		
		if (parkingLot < 1) {
			throw new ValidationException("Invalid ParkingLot : " + parkingLot + " The minimum Parking Lot : 1 ");
		}
		commandRequest.setCommand(currentCommand);
		commandRequest.setDataInput(String.valueOf(parkingLot));
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
		
		ParkingLot.createParkingLot(Integer.parseInt(request.getDataInput()));
		
		// Result Preparation
		commandResult.setSuccess(true);
		StringBuilder message = new StringBuilder("Created a parking lot with ")
		.append(request.getDataInput())
		.append(" slots");
		commandResult.setMessage(message.toString());
		
		logger.trace("Exit execute");
		return commandResult;
	}
}