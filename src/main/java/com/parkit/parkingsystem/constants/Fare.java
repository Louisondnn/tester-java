package com.parkit.parkingsystem.constants;

public class Fare {
    public static final double BIKE_RATE_PER_HOUR = 1.0;
    public static final double CAR_RATE_PER_HOUR = 1.5;
    // public static final long CAR_RATE = 0;
    // public static final long BIKE_RATE = 0;
    public static double calculateFare(ParkingType parkingType, long totalMinutes) {
        double ratePerHour = 0;

        switch (parkingType) {
            case CAR:
                ratePerHour = CAR_RATE_PER_HOUR;
                break;
            case BIKE:
                ratePerHour = BIKE_RATE_PER_HOUR;
                break;
            // Add more cases for different parking types if needed
            default:
                throw new IllegalArgumentException("Unknown parking type: " + parkingType);
        }
        double fare = Math.ceil(totalMinutes / 60.0) * ratePerHour; 
        return fare;
    }

}
