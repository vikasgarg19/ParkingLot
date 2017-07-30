package com.gojek.parking.system;


public class ParkingLotSystemTest {

	public static void main(String[] args) {
		// File Input
		ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
		System.out.println(parkingLotSystem.parkingLot("src//main//resources//input.txt"));
		
		
		// Interactive System
		//System.out.println(parkingLotSystem.parkingLot(""));
	}
}
