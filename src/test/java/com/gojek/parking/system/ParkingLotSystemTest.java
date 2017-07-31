package com.gojek.parking.system;

import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * To test ParkingLotSystem.
 * 
 * @author Vikas Garg
 *
 */
public class ParkingLotSystemTest {

	@Test
	public void parkingLot() {
		ParkingLotSystem system = new ParkingLotSystem();
		Assert.assertNotNull(system.parkingLot("src//test//resources//input.txt"));
	}
	
	@Test (enabled = false)
	public void testParkingLot() {
		ParkingLotSystem system = new ParkingLotSystem();
		Assert.assertNotNull(system.parkingLot(""));
	}
}