package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class FareCalculatorServiceTest {

    private final FareCalculatorService fareCalculatorService = new FareCalculatorService();

    @Test
    void testCalculateFare_CarLessThan30Minutes() {

        Ticket ticket = new Ticket(null, null);
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR);
        ticket.setParkingSpot(spot);
        // ticket.setInTime(new Date(System.currentTimeMillis()));
        // ticket.setOutTime(new Date(System.currentTimeMillis() + 15 * 60 * 1000)); // 15 minutes later
LocalDateTime inTime = LocalDateTime.now(); 
    ticket.setInTime(inTime); // Use LocalDateTime
    
    // Set outTime to 15 minutes later
    LocalDateTime outTime = inTime.plus(15, ChronoUnit.MINUTES); // Add 15 minutes
    ticket.setOutTime(outTime); // Use LocalDateTime


        double fare = fareCalculatorService.calculateFare(ticket);


        assertEquals(0, fare, 0.01);
    }

    @Test
    void testCalculateFare_CarMoreThan30Minutes() {

        Ticket ticket = new Ticket(null, null);
        ParkingSpot spot = new ParkingSpot(0, ParkingType.CAR);
        ticket.setParkingSpot(spot);
        LocalDateTime inTime = LocalDateTime.now(); 
        ticket.setInTime(inTime); // Use LocalDateTime
        
        LocalDateTime outTime = inTime.plus(2, ChronoUnit.HOURS); // Add 2 hours
        ticket.setOutTime(outTime); // Use LocalDateTime
    

        double fare = fareCalculatorService.calculateFare(ticket);

        double expectedFare = 2 * Fare.CAR_RATE_PER_HOUR; // Assuming CAR_RATE_PER_HOUR is defined
        assertEquals(expectedFare, fare, 3);
    }

}