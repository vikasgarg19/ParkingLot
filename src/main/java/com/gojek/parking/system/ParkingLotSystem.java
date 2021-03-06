package com.gojek.parking.system;

import org.apache.log4j.Logger;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.handler.IParkingLotHandler;
import com.gojek.parking.handler.impl.FileParkingLotHandler;
import com.gojek.parking.handler.impl.InteractiveParkingLotHandler;

/**
 * Entry Point Class for ParkingLotSystem.
 * 
 * @author Vikas Garg
 *
 */
public class ParkingLotSystem {

	private static final Logger logger = Logger.getLogger(ParkingLotSystem.class);
	
	public static void main(String[] args) {
		IParkingLotHandler parkingLotHandler = null;
		String input = null;
		if (args == null || args.length < 1) {
			// It is interactive Handler
			parkingLotHandler = new InteractiveParkingLotHandler();
		} else {
			input = args[0];
			// Consider this as FileSystem Handler
			parkingLotHandler = new FileParkingLotHandler();	
		}
		try {
			System.out.println(parkingLotHandler.handle(input));
		} catch (ParkingLotException e) {
			logger.error("ParkingLotSystem Error", e);
		}
	}
	
	/**
	 * To manage parkingLot
	 * 
	 * @param input :
	 * 		
	 * @return :
	 * 		Output of the parking lot system.
	 */
	public String parkingLot(String input) {
		IParkingLotHandler parkingLotHandler = null;
		
		if (input == null || input.isEmpty()) {
			// It is interactive Handler
			parkingLotHandler = new InteractiveParkingLotHandler();
		} else {
			// Consider this as FileSystem Handler
			parkingLotHandler = new FileParkingLotHandler();
		}
		
		try {
			return parkingLotHandler.handle(input);
		} catch (ParkingLotException e) {
			logger.error("ParkingLotSystem Error", e);
		}
		return null;
	}
}
