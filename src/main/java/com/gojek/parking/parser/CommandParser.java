package com.gojek.parking.parser;

import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.factory.CommandFactory;
import com.gojek.parking.request.CommandRequest;

public class CommandParser {

	public static CommandRequest parse(String input) throws ValidationException {
		return CommandFactory.parseInput(input);
	}
}