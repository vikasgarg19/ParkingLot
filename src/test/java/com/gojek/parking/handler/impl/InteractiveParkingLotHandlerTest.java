package com.gojek.parking.handler.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.handler.IParkingLotHandler;

public class InteractiveParkingLotHandlerTest {

	@Test (enabled = false)
	public void handle() {
		IParkingLotHandler handler = new InteractiveParkingLotHandler();
		boolean isException = false;
		try {
			handler.handle(null);
		} catch (ParkingLotException e) {
			isException = true;
		}
		Assert.assertTrue(isException);
	}
}
