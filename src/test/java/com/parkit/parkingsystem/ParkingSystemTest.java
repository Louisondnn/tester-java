package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.parkit.parkingsystem.service.ParkingSystem;

public class ParkingSystemTest {
    public void testNewUser() {
        ParkingSystem parkingSystem = new ParkingSystem(100.0);
        parkingSystem.enterGarage("ABC123");
        assertEquals(100.0, parkingSystem.calculateTariff("ABC123"), 0.01);
    }

    public void testRecurrentUser() {
        ParkingSystem parkingSystem = new ParkingSystem(100.0);
        parkingSystem.enterGarage("ABC123");
        parkingSystem.enterGarage("ABC123");
        assertEquals(95.0, parkingSystem.calculateTariff("ABC123"), 0.01);
    }

    public void testMultipleUsers() {
        ParkingSystem parkingSystem = new ParkingSystem(100.0);
        parkingSystem.enterGarage("ABC123");
        parkingSystem.enterGarage("DEF456");
        assertEquals(100.0, parkingSystem.calculateTariff("DEF456"), 0.01);
        assertEquals(95.0, parkingSystem.calculateTariff("ABC123"), 0.01);
    }
}
