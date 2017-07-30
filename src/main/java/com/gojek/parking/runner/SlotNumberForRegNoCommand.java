package com.gojek.parking.runner;

import org.apache.log4j.Logger;

import com.gojek.parking.domain.Car;
import com.gojek.parking.domain.ParkingLot;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * This class is for handling various cases of "SLOT_NUMBERS_FOR_REGISTRATION_NUMBER" command.
 * 
 * @author Vikas Garg
 *
 */
public class SlotNumberForRegNoCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(SlotNumberForRegNoCommand.class);
	
	public SlotNumberForRegNoCommand() {
		super.currentCommand = Command.SLOT_NUMBERS_FOR_REGISTRATION_NUMBER;
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
		String requestRegNumber = request.getCar().getRegistrationNumber();
		ParkingLot parkingLot = ParkingLot.getParkingLot();
		if (parkingLot != null) {
			Car[] cars = parkingLot.getParkingSlots();
			if (cars == null || cars.length <= 0) {
				message.append("No slot available with Registration Number : " + requestRegNumber);
			} else {
				int slotNumber = -1;
				for (int count = 0; count < cars.length; count++) {
					Car localCar = cars[count];
					if (localCar != null) {
						if (localCar.getRegistrationNumber().equalsIgnoreCase(requestRegNumber)) {
							slotNumber = count + 1;
						}
					}
				}
				if (slotNumber == -1) {
					message.append("Not Found");
				} else {
					message.append(slotNumber);
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
		if (commandRequest.getLineInput().length() < (currentCommand.getKey().length() + 2)) {
			throw new ValidationException("Invalid Input");
		}
		
		String[] inputs = commandRequest.getLineInput().split(" ");
		if (inputs.length < 2) {
			throw new ValidationException("");
		}
		commandRequest.setCommand(currentCommand); 
		Car car = new Car();
		car.setRegistrationNumber(inputs[1]);
		commandRequest.setCar(car);
	}
}