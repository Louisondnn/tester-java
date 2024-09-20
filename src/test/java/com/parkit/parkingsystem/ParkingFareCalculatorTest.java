package com.parkit.parkingsystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;


public class ParkingFareCalculatorTest {

    @Test
    public void calculateFareCarWithLessThan30minutesParkingTime() {
        // Arrange
        //Ticket ticket = new Ticket(VehicleType.CAR, 30); // 30 minutes 
        FareCalculatorService calculator = new FareCalculatorService();
        ParkingType parkingType = ParkingType.CAR;
        long duration = 5 * (1000 * 60 * 60);

        double fare = calculator.calculateFareBasedOnType(parkingType, duration);
        

        assertEquals(7.5, fare); 
    }

    // @Test
    // public void calculateFareBikeWithLessThan30minutesParkingTime() {
    //     // Arrange
    //     ParkingFareCalculator calculator = new ParkingFareCalculator();
    //     Ticket ticket = new Ticket(VehicleType.MOTO, 15); // 15 minutes de stationnement
    //     double fare = calculator.calculateFare(ticket);
    //     assertEquals(0, fare, 0.01); 
    // }
}
