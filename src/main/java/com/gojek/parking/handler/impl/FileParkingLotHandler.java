package com.gojek.parking.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gojek.parking.exception.FileReaderException;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.factory.CommandFactory;
import com.gojek.parking.handler.IParkingLotHandler;
import com.gojek.parking.parser.CommandParser;
import com.gojek.parking.reader.ParkingLotFileReader;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * The handler class which will process FileSystem.
 * 
 * @author Vikas Garg
 *
 */
public class FileParkingLotHandler implements IParkingLotHandler {

	private static final Logger logger = Logger.getLogger(FileParkingLotHandler.class);
	
	/**
	 * To handle Input for ParkingLot system.
	 * 
	 * @param inputFile :
	 * 		File Path and its name
	 * 
	 * @return :
	 * 		Output of the file execution.
	 * 		
	 * @throws ParkingLotException :
	 * 		Exception if there is any.
	 */
	public String handle(String inputFile) throws ParkingLotException {
		logger.trace("Enter handle");
		
		List<String> fileContents = null;
		try {
			 fileContents = ParkingLotFileReader.readFile(inputFile);
		} catch (FileReaderException e1) {
			throw new ParkingLotException("");
		}
		
		StringBuilder output = new StringBuilder("");
		
		List<CommandRequest> commandRequestList = new ArrayList<CommandRequest>();

		if (fileContents != null && fileContents.size() > 0) {
			for (String command : fileContents) {
				try {
					commandRequestList.add(CommandParser.parse(command));
				} catch (ValidationException e) {
					throw new ParkingLotException("");
				}
			}
		}
		if (commandRequestList != null && !commandRequestList.isEmpty()) {
			for (CommandRequest commandRequest : commandRequestList) {
				CommandResult result = CommandFactory.getCommand(commandRequest.getCommand()).execute(commandRequest);
				if (result.isSuccess()) {
					output.append(result.getMessage());
					output.append("\n");
				}
			}	
		}
		logger.trace("Exit handle");
		return output.toString();
	}
}