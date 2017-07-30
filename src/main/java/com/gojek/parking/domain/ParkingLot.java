package com.gojek.parking.domain;

public class ParkingLot {

	private static ParkingLot parkingLot;
	
	private Car[] parkingSlots;
	
	private ParkingLot(int noOfSlots) {
		parkingSlots = new Car[noOfSlots];
		for (int i = 0; i < noOfSlots; i++) {
			parkingSlots[i] = null;
		}
	}
	
	public static void createParkingLot(int noOfSlots) {
		if (parkingLot == null) {
			synchronized (ParkingLot.class) {
				if(parkingLot == null) {
					parkingLot = new ParkingLot(noOfSlots);
				}
			}
		}
	}
	
	public static ParkingLot getParkingLot() {
		return parkingLot;
	}
	
	public ParkingTicket parkCar(Car carToPark) {
		ParkingTicket ticket = null;
		for (int count = 0; count < parkingSlots.length; count++) {
			if (parkingSlots[count] == null) {
				ticket = new ParkingTicket(carToPark, count + 1);
				parkingSlots[count] = carToPark;
				break;
			}
		}
		return ticket;
	}
	
	public int unparkCar(int slotNumber) {
		parkingSlots[slotNumber - 1] = null;
		return slotNumber;
	}
	
	public Car[] getParkingSlots() {
		return parkingSlots;
	}
}