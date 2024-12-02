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
        
                // Calcul de la durée de stationnement
                Duration duration = Duration.between(ticket.getInTime(), ticket.getOutTime());
                long totalMinutes = duration.toMinutes();
                
                System.out.println("Total minutes: " + totalMinutes);
        
                // Initialisation des frais
                double fare = 0;
        
                // Règles de tarification
                if (totalMinutes < 30) { // Moins de 30 minutes
                    fare = 0;
                } else if (ticket.getParkingSpot().getParkingType() == ParkingType.CAR) {
                    // Calcul des frais pour les voitures
                    fare = (totalMinutes / 60) * Fare.CAR_RATE; // Frais par heure
                    if (totalMinutes % 60 > 0) { // Ajout des frais pour les minutes restantes
                        fare += (Fare.CAR_RATE / 60.0) * (totalMinutes % 60);
                    }
                } else if (ticket.getParkingSpot().getParkingType() == ParkingType.BIKE) {
                    // Calcul des frais pour les motos
                    fare = (totalMinutes / 60) * Fare.BIKE_RATE; // Frais par heure
                    if (totalMinutes % 60 > 0) { // Ajout des frais pour les minutes restantes
                        fare += (Fare.BIKE_RATE / 60.0) * (totalMinutes % 60);
                    }
                }
        
                ticket.setPrice(fare);
                System.out.println("Fare: " + fare);
                return fare;
            }

        }
    
    

    // public double calculateFare(Ticket ticket) {
       
    //     if (ticket == null || ticket.getInTime() == null || ticket.getOutTime() == null) {
    //         throw new IllegalArgumentException("Ticket or its in/out time cannot be null");
    //     }
    //     if (ticket.getInTime().isAfter(ticket.getOutTime())) {
    //         throw new IllegalArgumentException("In time cannot be greater than out time");
    //     }
    //     Duration duration = Duration.between(ticket.getInTime(), ticket.getOutTime());
    //     long totalMinutes = duration.toMinutes();
    //     long totalHours = duration.toHours();
    //     System.out.println("Total hours: " + totalHours);
    //     System.out.println("Total minutes: " + totalMinutes);
    //     System.out.println("Seconds: " + duration.getSeconds());
    
    //     double fare = 0;
    //     System.out.println("Parking Type: " + ticket.getParkingSpot().getParkingType());
    
    // if (duration.toMinutes() < 30 ) { // 30 minutes in milliseconds
    //     fare = 0;
    // } else if (ticket.getParkingSpot().getParkingType() == ParkingType.CAR) {
    //     fare = duration.toHours() * Fare.CAR_RATE;
    //     System.out.println("fare car" + Fare.CAR_RATE);
        
    // } else if (ticket.getParkingSpot().getParkingType() == ParkingType.BIKE) {
    //     fare = duration.toHours() * Fare.BIKE_RATE;
    // }
    // ticket.setPrice(fare);
    // System.out.println("Fare: " + fare);
    // return fare;

    // }
    // }




     

     