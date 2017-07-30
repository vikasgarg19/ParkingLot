package com.gojek.parking.factory;

import org.apache.log4j.Logger;

import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.runner.AbstractCommand;
import com.gojek.parking.runner.CreateParkingLotCommand;
import com.gojek.parking.runner.LeaveCommand;
import com.gojek.parking.runner.ParkCommand;
import com.gojek.parking.runner.RegistrationNoCarWithColourCommand;
import com.gojek.parking.runner.SlotForCarWithColourCommand;
import com.gojek.parking.runner.SlotNumberForRegNoCommand;
import com.gojek.parking.runner.StatusCommand;

public class CommandFactory {

	private static final Logger logger = Logger.getLogger(CommandFactory.class);
	
	public static AbstractCommand getExecutor(Command command) {
		logger.trace("Enter getExecutor with command : " + command);
		
		AbstractCommand executor = null;
		
		switch (command) {
			case CREATE_PARKING_LOT :
				executor = new CreateParkingLotCommand();
				break;
			case PARK :
				executor = new ParkCommand();
				break;
			case LEAVE :
				executor = new LeaveCommand();
				break;
			case STATUS:
				executor = new StatusCommand();
				break;
			case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR :
				executor = new RegistrationNoCarWithColourCommand();
				break;
			case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR :
				executor = new SlotForCarWithColourCommand();
				break;
			case SLOT_NUMBERS_FOR_REGISTRATION_NUMBER :
				executor = new SlotNumberForRegNoCommand();
				break;
			default :
				logger.error("Invalid Command : " + command.toString());
				break;
		}
		return executor;
	}
	
	public static CommandRequest parseInput(String input) throws ValidationException {
		AbstractCommand runner = null;
		if (input.toLowerCase().startsWith(Command.CREATE_PARKING_LOT.getKey())) {
			 runner = getExecutor(Command.CREATE_PARKING_LOT);
		} else if (input.toLowerCase().startsWith(Command.PARK.getKey())) {
			runner = getExecutor(Command.PARK);
		} else if (input.toLowerCase().startsWith(Command.LEAVE.getKey())) {
			runner = getExecutor(Command.LEAVE);
		} else if (input.toLowerCase().startsWith(Command.STATUS.getKey())) {
			runner = getExecutor(Command.STATUS);
		} else if (input.toLowerCase().startsWith(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.getKey())) {
			runner = getExecutor(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR);
		} else if (input.toLowerCase().startsWith(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.getKey())) {
			runner = getExecutor(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
		} else if (input.toLowerCase().startsWith(Command.SLOT_NUMBERS_FOR_REGISTRATION_NUMBER.getKey())) {
			runner = getExecutor(Command.SLOT_NUMBERS_FOR_REGISTRATION_NUMBER);
		}
		if (runner == null) {
			//throw new ValidationException("");
			System.out.println(input);
		}
		return runner.parseCommand(input);
	}
}