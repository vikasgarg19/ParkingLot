package com.gojek.parking.domain;

/**
 * To maintain ParkingTicket
 * 
 * @author Vikas Garg
 *
 */
public class ParkingTicket {

	// To maintain Parked Car
	private Car parkedCar;
	
	// To maintain the slot Number
	private int slotNumber;

	public ParkingTicket(Car carToPark, int slotNumber) {
		this.parkedCar = carToPark;
		this.slotNumber = slotNumber;
	}

	/**
	 * To get Parked Car
	 * 
	 * @return
	 */
	public Car getParkedCar() {
		return parkedCar;
	}

	/**
	 * To get Slot Number
	 * 
	 * @return
	 */
	public int getSlotNumber() {
		return slotNumber;
	}
}
