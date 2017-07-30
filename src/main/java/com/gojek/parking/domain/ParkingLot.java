package com.gojek.parking.domain;

/**
 * To maintain the ParkingLot System.
 * 
 * @author Vikas Garg
 *
 */
public class ParkingLot {

	// Pointer for ParkingLot
	private static ParkingLot parkingLot;
	
	// ParkingSlots
	private Car[] parkingSlots;
	
	/**
	 * Constructor which will initialize numberOfSlots
	 * 
	 * @param numberOfSlots
	 */
	private ParkingLot(int numberOfSlots) {
		parkingSlots = new Car[numberOfSlots];
		for (int i = 0; i < numberOfSlots; i++) {
			parkingSlots[i] = null;
		}
	}
	
	/**
	 * To create ParkingLot based on supplied numberOfSlots
	 * 
	 * @param numberOfSlots
	 */
	public static void createParkingLot(int numberOfSlots) {
		if (parkingLot == null) {
			synchronized (ParkingLot.class) {
				if(parkingLot == null) {
					parkingLot = new ParkingLot(numberOfSlots);
				}
			}
		}
	}
	
	/**
	 * To get current ParkingLot.
	 * 
	 * @return
	 */
	public static ParkingLot getParkingLot() {
		return parkingLot;
	}
	
	/**
	 * To park the car
	 * 
	 * @param carToPark :
	 * 		Car object which is required for Parking
	 * 
	 * @return :
	 * 		ParkingTicket which has information related to Parked Car
	 */
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
	
	/**
	 * To unpark the car from specified slotnumber.
	 * 
	 * @param slotNumber :
	 * 		
	 * @return
	 */
	public int unparkCar(int slotNumber) {
		parkingSlots[slotNumber - 1] = null;
		return slotNumber;
	}
	
	/**
	 * To get Parking Slots
	 * 
	 * @return
	 */
	public Car[] getParkingSlots() {
		return parkingSlots;
	}
}