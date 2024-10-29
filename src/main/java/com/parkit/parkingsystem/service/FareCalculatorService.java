package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;


public class FareCalculatorService {

    public double calculateFare(Ticket ticket) {
        if (ticket.getInTime().getTime() > ticket.getOutTime().getTime()) {
            throw new IllegalArgumentException("In time cannot be greater than out time");
        }

        long inTime = ticket.getInTime().getTime();
        long outTime = ticket.getOutTime().getTime();
        System.out.println("intime" + inTime);
        System.out.println("out" + outTime);

        long duration = ticket.getOutTime().getTime() - ticket.getInTime().getTime();
        System.out.println("duration" + duration);
        System.out.println("duration" + duration / (1000 * 60 * 60));

        double fare = 0;


    if (duration < 30 * 60 * 1000) { // 30 minutes in milliseconds
        fare = 0;
    } else if (ticket.getParkingSpot().getParkingType() == ParkingType.CAR) {
        fare = duration * Fare.CAR_RATE;
    } else if (ticket.getParkingSpot().getParkingType() == ParkingType.BIKE) {
        fare = duration * Fare.BIKE_RATE;
    }
    ticket.setPrice(fare);
    System.out.println("Fare: " + fare);
    return fare;

    }
    public double calculateFareBasedOnType(ParkingType parkingType, long duration) {
        double farePerHour = parkingType == ParkingType.CAR ? Fare.CAR_RATE_PER_HOUR : Fare.BIKE_RATE_PER_HOUR;
        double hours = (double) duration / (1000 * 60 * 60);
        double fare = hours * farePerHour;
        System.out.println("test" + farePerHour);
        System.out.println("test" + hours);

        return fare;
      }  


    }

    // tests fonctionnels 

     