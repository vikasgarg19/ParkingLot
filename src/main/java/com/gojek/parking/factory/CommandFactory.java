package com.gojek.parking.factory;

import org.apache.log4j.Logger;

import com.gojek.parking.command.AbstractCommand;
import com.gojek.parking.command.CreateParkingLotCommand;
import com.gojek.parking.command.LeaveCommand;
import com.gojek.parking.command.ParkCommand;
import com.gojek.parking.command.RegistrationNoCarWithColourCommand;
import com.gojek.parking.command.SlotForCarWithColourCommand;
import com.gojek.parking.command.SlotNumberForRegNoCommand;
import com.gojek.parking.command.StatusCommand;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;

/**
 * Factory class for Command
 * 
 * @author Vikas Garg
 *
 */
public class CommandFactory {

	private static final Logger logger = Logger.getLogger(CommandFactory.class);
	
	/**
	 * To get the Command based on supplied command object.
	 * @param command
	 * @return
	 */
	public static AbstractCommand getCommand(Command command) {
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
	
	/**
	 * To parse input based on supplied input line.
	 * 
	 * @param input :
	 * 		
	 * @return :
	 * 		
	 * @throws ValidationException
	 */
	public static CommandRequest parseInput(String input) throws ValidationException {
		AbstractCommand runner = null;
		if (input.toLowerCase().startsWith(Command.CREATE_PARKING_LOT.getKey())) {
			 runner = getCommand(Command.CREATE_PARKING_LOT);
		} else if (input.toLowerCase().startsWith(Command.PARK.getKey())) {
			runner = getCommand(Command.PARK);
		} else if (input.toLowerCase().startsWith(Command.LEAVE.getKey())) {
			runner = getCommand(Command.LEAVE);
		} else if (input.toLowerCase().startsWith(Command.STATUS.getKey())) {
			runner = getCommand(Command.STATUS);
		} else if (input.toLowerCase().startsWith(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.getKey())) {
			runner = getCommand(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR);
		} else if (input.toLowerCase().startsWith(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.getKey())) {
			runner = getCommand(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
		} else if (input.toLowerCase().startsWith(Command.SLOT_NUMBERS_FOR_REGISTRATION_NUMBER.getKey())) {
			runner = getCommand(Command.SLOT_NUMBERS_FOR_REGISTRATION_NUMBER);
		}
		if (runner == null) {
			//throw new ValidationException("");
		}
		return runner.parseCommand(input);
	}
}