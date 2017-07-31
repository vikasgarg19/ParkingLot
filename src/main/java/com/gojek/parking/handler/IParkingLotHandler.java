package com.gojek.parking.handler;

import com.gojek.parking.exception.ParkingLotException;

/**
 * Handler class for ParkingLot
 * 
 * @author Vikas Garg
 *
 */
public interface IParkingLotHandler {

	/**
	 * To handle Input for ParkingLot system.
	 * 
	 * @param input :
	 * 		
	 * @return :
	 * 		
	 * @throws ParkingLotException :
	 * 		Exception if there is any.
	 */
	public String handle(String input) throws ParkingLotException;
}