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
        double durationInHours = (double) duration / (1000 * 60 * 60);
        System.out.println("durationInHours" + durationInHours);
        // long durationInMilliseconds = duration.toMillis();
        // System.out.println("durationInMilliseconds" + durationInMilliseconds);

        double fare = calculateFareBasedOnType(ticket.getParkingSpot().getParkingType(), duration);
        ticket.setPrice(fare);
        // double durationInHours = (double) duration / (60 * 60 * 1000);
    }
    private double calculateFareBasedOnType(ParkingType parkingType, long duration) {
        double farePerHour = parkingType == ParkingType.CAR ? Fare.CAR_RATE_PER_HOUR : Fare.BIKE_RATE_PER_HOUR;
        double hours = (double) duration / (1000 * 60 * 60);
        double fare = hours * farePerHour;
        System.out.println("test" + farePerHour);
        System.out.println("test" + hours);

    //     double dailyMaxFare = parkingType == ParkingType.CAR ? Fare.CAR_DAILY_MAX : Fare.BIKE_DAILY_MAX;
    // if (fare > dailyMaxFare) {
    //     fare = dailyMaxFare;
    // }

        // if (ticket.getParkingSpot().getParkingType() == ParkingType.CAR) {
        //     fare = durationInHours * Fare.CAR_RATE_PER_HOUR;
        // } else if (ticket.getParkingSpot().getParkingType() == ParkingType.BIKE) {
        //     fare = durationInHours * Fare.BIKE_RATE_PER_HOUR;
        // } else {
        //     throw new NullPointerException("Parking type is not defined");
        // }
        return Math.round(fare * 1.000) / 1.000;
      }  

        // // Appliquer une réduction de 25% si la durée est inférieure à 1 heure
        // if (hours < 1) {
        //     fare *= 0.75;
        // }


    }

     