package com.gojek.parking.system;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.handler.IParkingLotHandler;
import com.gojek.parking.handler.impl.FileParkingLotHandler;
import com.gojek.parking.handler.impl.InteractiveParkingLotHandler;

public class ParkingLotSystem {

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
		}
		
		return null;
	}
}