package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;


 // convertir 'milisecondes'
public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
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
    System.out.println("Fare: " + fare);
    return;


        // double durationInHours = (double) duration / (1000 * 60 * 60);
        // System.out.println("durationInHours" + durationInHours);
        // // long durationInMilliseconds = duration.toMillis();
        // // System.out.println("durationInMilliseconds" + durationInMilliseconds);



        // double fare = calculateFareBasedOnType(ticket.getParkingSpot().getParkingType(), duration);
        // ticket.setPrice(fare);
        // double durationInHours = (double) duration / (60 * 60 * 1000);
    }
    public double calculateFareBasedOnType(ParkingType parkingType, long duration) {
        double farePerHour = parkingType == ParkingType.CAR ? Fare.CAR_RATE_PER_HOUR : Fare.BIKE_RATE_PER_HOUR;
        double hours = (double) duration / (1000 * 60 * 60);
        double fare = hours * farePerHour;
        System.out.println("test" + farePerHour);
        System.out.println("test" + hours);

        return fare;
      }  

        // // Appliquer une réduction de 25% si la durée est inférieure à 1 heure
        // if (hours < 1) {
        //     fare *= 0.75;
        // }


    }

     