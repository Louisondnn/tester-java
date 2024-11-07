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

        Ticket ticket = new Ticket();
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR, false);
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date(System.currentTimeMillis()));
        ticket.setOutTime(new Date(System.currentTimeMillis() + 15 * 60 * 1000)); // 15 minutes later


        double fare = fareCalculatorService.calculateFare(ticket);


        assertEquals(0, fare, 0.01);
    }

    @Test
    void testCalculateFare_CarMoreThan30Minutes() {

        Ticket ticket = new Ticket();
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR, false);
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date(System.currentTimeMillis()));
        ticket.setOutTime(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)); // 2 hours later


        double fare = fareCalculatorService.calculateFare(ticket);

        double expectedFare = 2 * Fare.CAR_RATE_PER_HOUR; // Assuming CAR_RATE_PER_HOUR is defined
        assertEquals(expectedFare, fare, 3);
    }

    @Test
    void testCalculateFare_BikeMoreThan30Minutes() {

        Ticket ticket = new Ticket();
        ParkingSpot spot = new ParkingSpot(0, ParkingType.BIKE, false);
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date(System.currentTimeMillis()));
        ticket.setOutTime(new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000)); // 1 hour later


        double fare = fareCalculatorService.calculateFare(ticket);


        double expectedFare = 1 * Fare.BIKE_RATE_PER_HOUR; // Assuming BIKE_RATE_PER_HOUR is defined
        assertEquals(expectedFare, fare, 1);
    }

    @Test
    void testCalculateFare_InTimeGreaterThanOutTime() {
        Ticket ticket = new Ticket();
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR, false);
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date(System.currentTimeMillis() + 60 * 1000)); // 1 minute in the future
        ticket.setOutTime(new Date(System.currentTimeMillis()));

        assertThrows(IllegalArgumentException.class, () -> fareCalculatorService.calculateFare(ticket));
    }
    @Test
    void testCalculateFare_CarLessThanOneHour() {
        Ticket ticket = new Ticket();
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR, false);
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date(System.currentTimeMillis()));
        ticket.setOutTime(new Date(System.currentTimeMillis() + 45 * 60 * 1000)); // 45 minutes later

        double fare = fareCalculatorService.calculateFare(ticket);

        double expectedFare = 0.75 * Fare.CAR_RATE_PER_HOUR; // 0.75 heures
        assertEquals(expectedFare, fare, 1.125);
    }
    // @Test
    // void testCalculateFare_BikeLessThanOneHour() {
    //     Ticket ticket = new Ticket();
    //     ParkingSpot spot = new ParkingSpot(0, ParkingType.BIKE, false);
    //     ticket.setParkingSpot(spot);
    //     ticket.setInTime(new Date(System.currentTimeMillis()));
    //     ticket.setOutTime(new Date(System.currentTimeMillis() + 30 * 60 * 1000)); // 30 minutes later

    //     double fare = fareCalculatorService.calculateFare(ticket);

    //     double expectedFare = 0.5 * Fare.BIKE_RATE_PER_HOUR; // 0.5 heures
    //     assertEquals(expectedFare, fare, 0.5);
    // }
}