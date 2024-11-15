package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {
    private ParkingSpot parkingSpot;

    @BeforeEach
    void setUp() {
        parkingSpot = new ParkingSpot(1, ParkingType.CAR);
    }

    @Test
    void testInitialization() {
        assertEquals(1, parkingSpot.getId());
        assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
        assertFalse(parkingSpot.isAvailable());
    }

    @Test
    void testSetId() {
        parkingSpot.setId(2);
        assertEquals(2, parkingSpot.getId());
    }

    @Test
    void testSetParkingType() {
        parkingSpot.setParkingType(ParkingType.BIKE);
        assertEquals(ParkingType.BIKE, parkingSpot.getParkingType());
    }

    @Test
    void testSetAvailable() {
        parkingSpot.setAvailable(false);
        assertFalse(parkingSpot.isAvailable());
    }

    @Test
    void testEqualsAndHashCode() {
        ParkingSpot anotherSpot = new ParkingSpot(1, ParkingType.CAR);
        ParkingSpot differentSpot = new ParkingSpot(2, ParkingType.BIKE);

        assertEquals(parkingSpot, anotherSpot);
        assertNotEquals(parkingSpot, differentSpot);
        assertEquals(parkingSpot.hashCode(), anotherSpot.hashCode());
        assertNotEquals(parkingSpot.hashCode(), differentSpot.hashCode());
    }
}