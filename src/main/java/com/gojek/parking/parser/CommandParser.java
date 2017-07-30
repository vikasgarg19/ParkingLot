package com.gojek.parking.parser;

import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.factory.CommandFactory;
import com.gojek.parking.request.CommandRequest;

/**
 * Parser class for command parsing
 * 
 * @author Vikas Garg
 *
 */
public class CommandParser {

	/**
	 * To parse the command based on supplied input
	 * 
	 * @param input :
	 * 		String value of command
	 * 
	 * @return :
	 * 		CommandRequest object which contains all the information related to command
	 * 
	 * @throws ValidationException :
	 * 		If the command is in invalid format.
	 */
	public static CommandRequest parse(String input) throws ValidationException {
		return CommandFactory.parseInput(input);
	}
}