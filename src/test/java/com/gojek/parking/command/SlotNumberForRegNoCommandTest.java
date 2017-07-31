package com.gojek.parking.command;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.gojek.parking.exception.ValidationException;
import com.gojek.parking.request.CommandRequest;
import com.gojek.parking.response.CommandResult;

/**
 * Maintains various test cases related to SLOT_NUMBERS_FOR_REGISTRATION_NUMBER Command
 * 
 * @author Vikas Garg
 */
public class SlotNumberForRegNoCommandTest {

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
		
		SlotNumberForRegNoCommand registrationNoCommand = new SlotNumberForRegNoCommand();
		commandRequest = new CommandRequest();
		commandRequest.setLineInput("slot_number_for_registration_number KA-01-HH-1234");
		
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
		Assert.assertTrue(result.getMessage().contains("1"));
	}

	@Test
	public void executeWithNoResponse() {
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
		
		SlotNumberForRegNoCommand registrationNoCommand = new SlotNumberForRegNoCommand();
		commandRequest = new CommandRequest();
		commandRequest.setLineInput("slot_number_for_registration_number KA-01-HH-1235");
		
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
		Assert.assertTrue(result.getMessage().contains("Not Found"));
	}
	
	@Test
	public void validateCommand() {
		SlotNumberForRegNoCommand command = new SlotNumberForRegNoCommand();
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
		SlotNumberForRegNoCommand command = new SlotNumberForRegNoCommand();
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
		SlotNumberForRegNoCommand command = new SlotNumberForRegNoCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("slot_number_for_registration_number ");
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
		SlotNumberForRegNoCommand command = new SlotNumberForRegNoCommand();
		CommandRequest commandRequest = new CommandRequest();
		commandRequest.setLineInput("slot_number_for_registration_number KA-01-HH-9999");
		boolean isExceptionThrown = false;
		try {
			command.validateCommand(commandRequest);
		} catch (ValidationException e) {
			isExceptionThrown = true;
		}
		Assert.assertTrue(!isExceptionThrown);
	}
}