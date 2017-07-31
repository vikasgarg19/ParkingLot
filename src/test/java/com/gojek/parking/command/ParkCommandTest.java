package com.gojek.parking.command;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * Maintains various test cases related to Park Command
 * 
 * @author Vikas Garg
 */
public class ParkCommandTest {

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
	}

	@Test
	public void validateCommand() {
		ParkCommand command = new ParkCommand();
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
		ParkCommand command = new ParkCommand();
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
		ParkCommand command = new ParkCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("park ");
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
		ParkCommand command = new ParkCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("park KA-01-HH-1234 White");
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
	}
}
