package com.gojek.parking.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gojek.parking.exception.FileReaderException;

/**
 * FileReader class to read a file
 * 
 * @author Vikas Garg
 *
 */
public class ParkingLotFileReader {

	private static final Logger logger = Logger.getLogger(ParkingLotFileReader.class);
	
	/**
	 * To read the file and provide the contents of the file.
	 * 
	 * @param inputFile :
	 * 		File Name and its path.
	 * 
	 * @return :
	 * 		Content of the files.
	 * 
	 * @throws FileReaderException :
	 * 		If there is any exception in reading the file.
	 */
	public static List<String> readFile(String inputFile) throws FileReaderException {
		
		if (inputFile == null || inputFile.isEmpty()) {
			logger.error("Invalid InputFile Path");
			throw new FileReaderException("InputFile Path is Empty or Null.");
		}
		
		List<String> commandsList = new ArrayList<String>();
		
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(inputFile);
			bufferedReader = new BufferedReader(fileReader);

			String sCurrentLine;

			while ((sCurrentLine = bufferedReader.readLine()) != null) {
				if (sCurrentLine != null && !sCurrentLine.isEmpty()) {
					commandsList.add(sCurrentLine);
				}
			}
		} catch (IOException e) {
			logger.error("Exception while readFile", e);
			throw new FileReaderException("Unable to read file", e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException ex) {
				logger.error("Exception while stream handling", ex);
				throw new FileReaderException("Unable to read file", ex);
			}
		}
		return commandsList;
	}
}