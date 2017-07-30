package com.gojek.parking.runner;

import org.apache.log4j.Logger;

import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * Generic class for various operations related to various commands.
 * 
 * Operations like parsing the command, validation of command and execution of command will be taken care by this class 
 * and it's children.
 * 
 * @author Vikas Garg
 *
 */
public abstract class AbstractCommand {

	private static final Logger logger = Logger.getLogger(AbstractCommand.class);
	
	// To maintain the current command
	protected Command currentCommand = null;
	
	/**
	 * To parse the input string which contains the details of command.
	 * 
	 * @param input :
	 * 		Input String which has details of command.
	 * 
	 * @return :
	 * 		Request object which has details of command.
	 * 
	 * @throws ValidationException :
	 * 		If there is any invalid command.
	 */
	public CommandRequest parseCommand(String input) throws ValidationException {
		logger.trace("Enter parseInputCommand");
		
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput(input);
		
		if (commandRequest.getLineInput() == null || commandRequest.getLineInput().length() == 0) {
			throw new ValidationException("Empty Input supplied : " + commandRequest.getLineInput());
		}
		
		validateCommand(commandRequest);
		
		logger.trace("Exit parseInputCommand");
		
		return commandRequest;
	}

	/**
	 * To validate the command which has been requested by the client. This method will validate all the inputs of 
	 * command.
	 * 
	 * @param commandRequest :
	 * 		CommandRequest object which contains messsage.
	 * 
	 * @throws ValidationException :
	 * 		If there is failure in command validation
	 */
	public abstract void validateCommand(CommandRequest commandRequest) throws ValidationException;
	
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
	public abstract CommandResult execute(CommandRequest request);
}