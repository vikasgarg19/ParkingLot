package com.gojek.parking.command;

import org.apache.log4j.Logger;

import com.gojek.parking.domain.ParkingLot;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * This class is for handling various cases of "LEAVE" command.
 * 
 * @author Vikas Garg
 *
 */
public class LeaveCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(LeaveCommand.class);
	
	public LeaveCommand() {
		super.currentCommand = Command.LEAVE;
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
		int freeSlot = -1;
		if (parkingLot != null) {
			freeSlot = parkingLot.unparkCar(Integer.parseInt(request.getDataInput()));
		}
		// Result Preparation
		commandResult.setSuccess(true);
		StringBuilder message = new StringBuilder("Slot number ");
		message.append(freeSlot);
		message.append(" is free");
		commandResult.setMessage(message.toString());
		
		logger.trace("Exit execute");
		return commandResult;
	}

	/**
	 * To validate the command which has been requested by the client. This method will validate all the inputs of 
	 * command LEAVE
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
		
		Integer leaveSlotNumber = null;
		
		try {
			leaveSlotNumber = Integer.parseInt(inputs[1]);
		} catch (NumberFormatException exception) {
			throw new ValidationException("Invalid Leave Slot Number ", exception);
		}
		
		if (leaveSlotNumber < 1) {
			throw new ValidationException("Invalid Leave Slot Number : " + leaveSlotNumber);
		}
	
		commandRequest.setCommand(Command.LEAVE);
		commandRequest.setDataInput(String.valueOf(leaveSlotNumber));
		logger.trace("Exit validateCommand");
	}
}