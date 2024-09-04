package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if (ticket.getInTime().getTime() > ticket.getOutTime().getTime()) {
            throw new IllegalArgumentException("In time cannot be greater than out time");
        }

        long inTime = ticket.getInTime().getTime();
        long outTime = ticket.getOutTime().getTime();
        long duration = ticket.getOutTime().getTime() - ticket.getInTime().getTime();
        double fare = calculateFareBasedOnType(ticket.getParkingType(), duration);
        ticket.setPrice(fare);

        double durationInHours = (double) duration / (60 * 60 * 1000);

        double fare = 0;

    private double calculateFareBasedOnType(ParkingType parkingType, long duration) {
        double farePerHour = parkingType == ParkingType.CAR ? Fare.CAR_RATE_PER_HOUR : Fare.BIKE_RATE_PER_HOUR;
        double hours = (double) duration / (1000 * 60 * 60);
        double fare = hours * farePerHour;

        double dailyMaxFare = parkingType == ParkingType.CAR ? Fare.CAR_DAILY_MAX : Fare.BIKE_DAILY_MAX;
    if (fare > dailyMaxFare) {
        fare = dailyMaxFare;
    }
    return Math.round(fare * 100.0) / 100.0;
}

        if (ticket.getParkingSpot().getParkingType() == ParkingType.CAR) {
            fare = durationInHours * Fare.CAR_RATE_PER_HOUR;
        } else if (ticket.getParkingSpot().getParkingType() == ParkingType.BIKE) {
            fare = durationInHours * Fare.BIKE_RATE_PER_HOUR;
        } else {
            throw new NullPointerException("Parking type is not defined");
        }

        if (durationInHours < 1) {
            fare = fare * 0.75;
        }

        ticket.setPrice(fare);

        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }

    }
