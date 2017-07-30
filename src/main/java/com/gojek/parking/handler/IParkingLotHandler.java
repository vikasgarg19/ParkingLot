package com.gojek.parking.handler;

import com.gojek.parking.exception.ParkingLotException;

public interface IParkingLotHandler {

	public String handle(String input) throws ParkingLotException;
}