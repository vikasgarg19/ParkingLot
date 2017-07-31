package com.gojek.parking.command;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * Maintains various test cases related to REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR Command
 * 
 * @author Vikas Garg
 */
public class RegistrationNoCarWithColourCommandTest {

	@Test
	public void execute() {
		CreateParkingLotCommand command = new CreateParkingLotCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("create_parking_lot 6");
		
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		CommandResult result = command.execute(commandRequest);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isSuccess());
		Assert.assertEquals("Created a parking lot with 6 slots" , result.getMessage());
		
		ParkCommand parkCommand = new ParkCommand();
		commandRequest = new CommandRequest();
		commandRequest.setLineInput("park KA-01-HH-1234 White");
		
		isExceptionThrown = false;
		try {
			parkCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		result = parkCommand.execute(commandRequest);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isSuccess());
		//Assert.assertEquals("Allocated slot number: 1" , result.getMessage());
		
		RegistrationNoCarWithColourCommand registrationNoCommand = new RegistrationNoCarWithColourCommand();
		commandRequest = new CommandRequest();
		commandRequest.setLineInput("registration_numbers_for_cars_with_colour White");
		
		isExceptionThrown = false;
		try {
			registrationNoCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		result = registrationNoCommand.execute(commandRequest);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isSuccess());
		Assert.assertTrue(result.getMessage().contains("KA-01-HH-1234"));
	}

	@Test
	public void executeWithEmptyResponse() {
		CreateParkingLotCommand command = new CreateParkingLotCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("create_parking_lot 6");
		
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		CommandResult result = command.execute(commandRequest);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isSuccess());
		Assert.assertEquals("Created a parking lot with 6 slots" , result.getMessage());
		
		ParkCommand parkCommand = new ParkCommand();
		commandRequest = new CommandRequest();
		commandRequest.setLineInput("park KA-01-HH-1234 Black");
		
		isExceptionThrown = false;
		try {
			parkCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		result = parkCommand.execute(commandRequest);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isSuccess());
		//Assert.assertEquals("Allocated slot number: 1" , result.getMessage());
		
		RegistrationNoCarWithColourCommand registrationNoCommand = new RegistrationNoCarWithColourCommand();
		commandRequest = new CommandRequest();
		commandRequest.setLineInput("registration_numbers_for_cars_with_colour Blue");
		
		isExceptionThrown = false;
		try {
			registrationNoCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		result = registrationNoCommand.execute(commandRequest);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isSuccess());
		//Assert.assertTrue(result.getMessage().contains("No Registration Number Car available for Colour : BLUE"));
	}
	
	@Test
	public void validateCommand() {
		RegistrationNoCarWithColourCommand command = new RegistrationNoCarWithColourCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("");
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	@Test
	public void validateCommandForLength() {
		RegistrationNoCarWithColourCommand command = new RegistrationNoCarWithColourCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("");
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	@Test
	public void validateCommandForNull() {
		RegistrationNoCarWithColourCommand command = new RegistrationNoCarWithColourCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("registration_numbers_for_cars_with_colour ");
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	
	@Test
	public void validateCommandWithValid() {
		RegistrationNoCarWithColourCommand command = new RegistrationNoCarWithColourCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("registration_numbers_for_cars_with_colour White");
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
	}
}