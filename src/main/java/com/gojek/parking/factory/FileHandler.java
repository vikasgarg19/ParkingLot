package com.gojek.parking.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gojek.parking.exception.FileReaderException;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.parser.CommandParser;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

public class FileHandler {

	private static final Logger logger = Logger.getLogger(FileHandler.class);
	
	public String handle(String inputFile) throws FileReaderException {
		logger.trace("Enter handle");
		
		if (inputFile == null || inputFile.isEmpty()) {
			logger.error("Invalid InputFile Path");
			throw new FileReaderException("InputFile Path is Empty or Null.");
		}
		
		StringBuilder output = new StringBuilder("");
		
		List<CommandRequest> commandRequestList = new ArrayList<CommandRequest>();
		
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				commandRequestList.add(CommandParser.parse(sCurrentLine));
			}
		} catch (IOException | ValidationException e) {
			logger.error("Exception while readFile", e);
			throw new FileReaderException("Unable to read file", e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException ex) {
				logger.error("Exception while stream handling", ex);
				throw new FileReaderException("Unable to read file", ex);
			}
		}
		
		if (commandRequestList != null && !commandRequestList.isEmpty()) {
			for (CommandRequest commandRequest : commandRequestList) {
				CommandResult result = CommandFactory.getExecutor(commandRequest.getCommand()).execute(commandRequest);
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