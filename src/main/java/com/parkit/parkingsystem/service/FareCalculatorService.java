package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;
import java.time.Duration;
import java.time.LocalDateTime;

 // if (ticket.getInTime() > ticket.getOutTime()) {
        //     throw new IllegalArgumentException("In time cannot be greater than out time");
        // }

        // long inTime = ticket.getInTime().getTime();
        // long outTime = ticket.getOutTime().getTime();
        // System.out.println("intime" + inTime);
        // System.out.println("out" + outTime);

        // long duration = ticket.getOutTime().getTime() - ticket.getInTime().getTime();
        // System.out.println("duration" + duration);
        // System.out.println("duration" + duration * (1000 * 60 * 60));

        public class FareCalculatorService {
        
            public double calculateFare(Ticket ticket) {
                if (ticket == null || ticket.getInTime() == null || ticket.getOutTime() == null) {
                    throw new IllegalArgumentException("Ticket or its in/out time cannot be null");
                }
                if (ticket.getInTime().isAfter(ticket.getOutTime())) {
                    throw new IllegalArgumentException("In time cannot be greater than out time");
                }
        
                // Calculate the duration of parking
                Duration duration = Duration.between(ticket.getInTime(), ticket.getOutTime());
                long totalMinutes = duration.toMinutes();
        
                // Initialize fare
                double fare= 0;
                System.out.println("Total minutes: " + totalMinutes);
                if (totalMinutes >= 30) {
                    double ratePerHour = 0;
            
                    switch (ticket.getParkingSpot().getParkingType()) {
                        case CAR:
                            ratePerHour = Fare.CAR_RATE_PER_HOUR;
                            break;
                        case BIKE:
                            ratePerHour = Fare.BIKE_RATE_PER_HOUR;
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown parking type: " + ticket.getParkingSpot().getParkingType());
                    }
                                fare = (totalMinutes / 60) * ratePerHour; // Charge per hour
                    fare += (ratePerHour / 60.0) * (totalMinutes % 60); // Charge for remaining minutes
                }
            
                return fare;
            }            
        }
        