package com.gojek.parking.handler.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.handler.IParkingLotHandler;

/**
 * To test FileParkingLotHandler class.
 * 
 * @author Vikas Garg
 */
public class FileParkingLotHandlerTest {
	
	@Test
	public void testHandleWithEmptyInput() {
		IParkingLotHandler handler = new FileParkingLotHandler();
		boolean isException = false;
		try {
			handler.handle(null);
		} catch (ParkingLotException e) {
			isException = true;
		}
		Assert.assertTrue(isException);
	}
	
	@Test
	public void testHandleWithInvalidFileLocation() {
		IParkingLotHandler handler = new FileParkingLotHandler();
		boolean isException = false;
		try {
			handler.handle("C:\\Input.csv");
		} catch (ParkingLotException e) {
			isException = true;
		}
		Assert.assertTrue(isException);
	}
	
	@Test
	public void testHandleWithValidFileLocation() {
		IParkingLotHandler handler = new FileParkingLotHandler();
		boolean isException = false;
		String output = null;
		try {
			output = handler.handle("src//test//resources//input.txt");
		} catch (ParkingLotException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertNotNull(output);
	}
}