package com.gojek.parking.command;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * Maintains various test cases related to Leave Command
 * 
 * @author Vikas Garg
 */
public class LeaveCommandTest {

	@Test (priority = 0)
	public void execute() {
		CreateParkingLotCommand createCommand = new CreateParkingLotCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("create_parking_lot 6");
		
		boolean isExceptionThrown = false;
		try {
			createCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		CommandResult result = createCommand.execute(commandRequest);
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
	
		LeaveCommand command = new LeaveCommand();
		commandRequest = new CommandRequest();
		commandRequest.setLineInput("leave 1");
		
		isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
		
		result = command.execute(commandRequest);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isSuccess());
		//Assert.assertEquals("Slot number 1 is free" , result.getMessage());
	}

	@Test (priority = 1)
	public void validateCommand() {
		LeaveCommand createCommand = new LeaveCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("");
		boolean isExceptionThrown = false;
		try {
			createCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	@Test (priority = 2)
	public void validateCommandForLength() {
		LeaveCommand createCommand = new LeaveCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("");
		boolean isExceptionThrown = false;
		try {
			createCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	@Test (priority = 3)
	public void validateCommandForNull() {
		LeaveCommand createCommand = new LeaveCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("leave ");
		boolean isExceptionThrown = false;
		try {
			createCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	@Test (priority = 4)
	public void validateCommandForStringLot() {
		LeaveCommand createCommand = new LeaveCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("leave a");
		boolean isExceptionThrown = false;
		try {
			createCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	@Test (priority = 5)
	public void validateCommandForZeroLot() {
		LeaveCommand createCommand = new LeaveCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("leave 0");
		boolean isExceptionThrown = false;
		try {
			createCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(isExceptionThrown);
	}
	
	@Test (priority = 6)
	public void validateCommandWithValid() {
		LeaveCommand createCommand = new LeaveCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("leave 6");
		boolean isExceptionThrown = false;
		try {
			createCommand.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
	}
}