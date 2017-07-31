package com.gojek.parking.factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gojek.parking.command.AbstractCommand;
import com.gojek.parking.enums.Colour;
import com.gojek.parking.enums.Command;
import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;

/**
 * To test CommandFactory class
 * 
 * @author Vikas Garg
 *
 */
public class CommandFactoryTest {
	
	@Test
	public void testGetCommand() {
		AbstractCommand command = CommandFactory.getCommand(Command.CREATE_PARKING_LOT);
		Assert.assertNotNull(command);
	}
	
	@Test
	public void testParseInput() {
		boolean isException = false;
		try {
			@SuppressWarnings("unused")
			CommandRequest request = CommandFactory.parseInput("");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(isException);
	}
	
	@Test
	public void testCreateParkingLot() {
		boolean isException = false;
		CommandRequest request = null;
		try {
			request = CommandFactory.parseInput("create_parking_lot 6");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertTrue(request.getCommand().equals(Command.CREATE_PARKING_LOT));
		Assert.assertTrue(request.getDataInput().equalsIgnoreCase("6"));
	}
	
	@Test
	public void testPark() {
		boolean isException = false;
		CommandRequest request = null;
		try {
			request = CommandFactory.parseInput("park KA-01-HH-1234 White");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertTrue(request.getCommand().equals(Command.PARK));
		Assert.assertNotNull(request.getCar());
		Assert.assertTrue(request.getCar().getRegistrationNumber().equalsIgnoreCase("KA-01-HH-1234"));
		Assert.assertTrue(request.getCar().getColour().equals(Colour.WHITE));
	}
	
	@Test
	public void testLeave() {
		boolean isException = false;
		CommandRequest request = null;
		try {
			request = CommandFactory.parseInput("leave 4");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertTrue(request.getCommand().equals(Command.LEAVE));
		Assert.assertTrue(request.getDataInput().equalsIgnoreCase("4"));
	}
	
	@Test
	public void testStatus() {
		boolean isException = false;
		CommandRequest request = null;
		try {
			request = CommandFactory.parseInput("status");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertTrue(request.getCommand().equals(Command.STATUS));
	}
	
	@Test
	public void testRegistrationNumberForColour() {
		boolean isException = false;
		CommandRequest request = null;
		try {
			request = CommandFactory.parseInput("registration_numbers_for_cars_with_colour White");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertTrue(request.getCommand().equals(Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR));
		Assert.assertNotNull(request.getCar());
		Assert.assertTrue(request.getCar().getColour().equals(Colour.WHITE));
	}
	
	@Test
	public void testSlotNumberForColour() {
		boolean isException = false;
		CommandRequest request = null;
		try {
			request = CommandFactory.parseInput("slot_numbers_for_cars_with_colour White");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertTrue(request.getCommand().equals(Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR));
		Assert.assertNotNull(request.getCar());
		Assert.assertTrue(request.getCar().getColour().equals(Colour.WHITE));
	}
	
	@Test
	public void testSlotNumberForRegistrationNo() {
		boolean isException = false;
		CommandRequest request = null;
		try {
			request = CommandFactory.parseInput("slot_number_for_registration_number KA-01-HH-9999");
		} catch (ValidationException e) {
			isException = true;
		}
		Assert.assertTrue(!isException);
		Assert.assertTrue(request.getCommand().equals(Command.SLOT_NUMBERS_FOR_REGISTRATION_NUMBER));
		Assert.assertNotNull(request.getCar());
		Assert.assertTrue(request.getCar().getRegistrationNumber().equalsIgnoreCase("KA-01-HH-9999"));
	}
}