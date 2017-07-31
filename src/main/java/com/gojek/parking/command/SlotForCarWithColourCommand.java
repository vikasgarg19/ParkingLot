package com.gojek.parking.command;

import org.apache.log4j.Logger;

import com.gojek.parking.domain.Car;
import com.gojek.parking.domain.ParkingLot;
import com.gojek.parking.enums.Colour;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * This class is for handling various cases of "SLOT_NUMBERS_FOR_CARS_WITH_COLOUR" command.
 * 
 * @author Vikas Garg
 *
 */
public class SlotForCarWithColourCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(SlotForCarWithColourCommand.class);
	
	public SlotForCarWithColourCommand() {
		super.currentCommand = Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR;
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
		logger.trace("Enter executeCommand");
		CommandResult commandResult = new CommandResult();
		
		StringBuilder message = new StringBuilder("");
		
		Colour requestColour = request.getCar().getColour();
		
		ParkingLot parkingLot = ParkingLot.getParkingLot();
		if (parkingLot != null) {
			Car[] cars = parkingLot.getParkingSlots();
			if (cars == null || cars.length <= 0) {
				message.append("No slot available with Colour : " + requestColour.name());
			} else {
				Car matchedCar = null;
				for (int count = 0; count < cars.length; count++) {
					Car localCar = cars[count];
					if (localCar != null) {
						if (localCar.getColour().equals(requestColour)) {
							matchedCar = localCar;
							message.append(count+1);
							if (count < cars.length) {
								message.append(", ");
							}
						}
					}
				}
				if (matchedCar == null) {
					message.append("No slot available with Colour : " + requestColour.name());
				}
			}
		}
		// Result Preparation
		commandResult.setSuccess(true);
		commandResult.setMessage(message.toString());
		logger.trace("Exit executeCommand");
		return commandResult;
	}

	/**
	 * To validate the command which has been requested by the client. This method will validate all the inputs of 
	 * command SLOT_NUMBERS_FOR_CARS_WITH_COLOUR
	 * 
	 * @param commandRequest :
	 * 		CommandRequest object which contains message.
	 * 
	 * @throws ValidationException :
	 * 		If there is failure in command validation
	 */
	@Override
	public void validateCommand(CommandRequest commandRequest) throws ValidationException {
		if (commandRequest.getLineInput().length() < (currentCommand.getKey().length() + 2)) {
			throw new ValidationException("Invalid Input");
		}
		
		String[] inputs = commandRequest.getLineInput().split(" ");
		if (inputs.length < 2) {
			throw new ValidationException("Invalid Command Format for : " + currentCommand.name() + 
				commandRequest.getLineInput());
		}
		commandRequest.setCommand(currentCommand); 
		Car car = new Car();
		car.setColour(Colour.getColourByName(inputs[1]));
		commandRequest.setCar(car);
	}
}