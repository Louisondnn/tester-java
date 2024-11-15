package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FareCalculatorServiceTest {

    private final FareCalculatorService fareCalculatorService = new FareCalculatorService();

    @Test
    void testCalculateFare_CarLessThan30Minutes() {

        Ticket ticket = new Ticket(null, null);
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR);
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date(System.currentTimeMillis()));
        ticket.setOutTime(new Date(System.currentTimeMillis() + 15 * 60 * 1000)); // 15 minutes later


        double fare = fareCalculatorService.calculateFare(ticket);


        assertEquals(0, fare, 0.01);
    }

    @Test
    void testCalculateFare_CarMoreThan30Minutes() {

        Ticket ticket = new Ticket(null, null);
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR);
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date(System.currentTimeMillis()));
        ticket.setOutTime(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)); // 2 hours later


        double fare = fareCalculatorService.calculateFare(ticket);

        double expectedFare = 2 * Fare.CAR_RATE_PER_HOUR; // Assuming CAR_RATE_PER_HOUR is defined
        assertEquals(expectedFare, fare, 3);
    }

}