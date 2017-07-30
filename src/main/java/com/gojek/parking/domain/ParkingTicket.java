package com.gojek.parking.domain;

public class ParkingTicket {

	private Car parkedCar;
	private int slotNumber;

	public ParkingTicket(Car carToPark, int slotNumber) {
		this.parkedCar = carToPark;
		this.slotNumber = slotNumber;
	}

	public Car getParkedCar() {
		return parkedCar;
	}

	public int getSlotNumber() {
		return slotNumber;
	}
}
