package com.gojek.parking.enums;

/**
 * To maintain various command in ParkingLotSystem.
 * 
 * @author Vikas Garg
 */
public enum Command {

	CREATE_PARKING_LOT("create_parking_lot"), 
	PARK("park"),
	LEAVE("leave"),
	STATUS("status"),
	REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
	SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
	SLOT_NUMBERS_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number");
	
	private String key;
	
	Command(String commandKey) {
		this.key = commandKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}