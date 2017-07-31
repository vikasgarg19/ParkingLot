package com.gojek.parking.handler.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.factory.CommandFactory;
import com.gojek.parking.handler.IParkingLotHandler;
import com.gojek.parking.parser.CommandParser;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * The handler class which will process Interactive command system.
 * 
 * @author Vikas Garg
 *
 */
public class InteractiveParkingLotHandler implements IParkingLotHandler {

	private static final Logger logger = Logger.getLogger(InteractiveParkingLotHandler.class);
	
	/**
	 * To handle Input for ParkingLot system.
	 * 
	 * @param input :
	 * 
	 * @return :
	 * 		Output of the file execution.
	 * 		
	 * @throws ParkingLotException :
	 * 		Exception if there is any.
	 */
	public String handle(String input) throws ParkingLotException {
		String output = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String line = null;
			try {
				line = reader.readLine();
				if (line == null || line.trim().equals("")) {
					break;
				}
			} catch (IOException e) {
				logger.error("Unable to read command from console", e);
				throw new ParkingLotException("Unable to read command from console");
			}
			CommandRequest commandRequest = null;
			try {
				commandRequest = CommandParser.parse(line);
			} catch (ValidationException e) {
				e.printStackTrace();
			}
			if (commandRequest != null) {
				CommandResult result = CommandFactory.getCommand(commandRequest.getCommand()).execute(commandRequest);
				if (result.isSuccess()) {
					output = result.getMessage();
					System.out.println(output);
				}
			}
		}
		try {
			reader.close();
		} catch (IOException e) {
			logger.error("Exception while closing reader stream", e);
			throw new ParkingLotException("Exception while closing reader stream");
		}
		return output;
	}
}