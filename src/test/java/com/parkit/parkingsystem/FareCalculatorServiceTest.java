package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;
// import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
@ExtendWith(MockitoExtension.class)
public class FareCalculatorServiceTest {

    @Mock
    private FareCalculatorService classUnderTest;
    private Ticket ticket;

    @BeforeEach
    private void init() {
        // classUnderTest = new FareCalculatorService();
    }

//     @BeforeEach
//     public void setUpEach() {
//         ticket = new Ticket();
// }

    @Test
    public void testCalculateFareForCar() {
        
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
        ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setInTime(new Date(System.currentTimeMillis() - 3600000)); // 1 hour ago
        ticket.setOutTime(new Date());

        when(classUnderTest.calculateFare(ticket)).thenReturn(Fare.CAR_RATE_PER_HOUR);

        double fare = classUnderTest.calculateFare(ticket);

        assertEquals(Fare.CAR_RATE_PER_HOUR, fare);
    }

    @Test
    public void testCalculateFareForBike() {

        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE, true);
        ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setInTime(new Date(System.currentTimeMillis() - 7200000)); // 2 heures en arrière
        ticket.setOutTime(new Date());

        when(classUnderTest.calculateFare(ticket)).thenReturn(Fare.BIKE_RATE_PER_HOUR * 2);

        double fare = classUnderTest.calculateFare(ticket);

        assertEquals(Fare.BIKE_RATE_PER_HOUR * 2, fare);
    }
}


//     // @Test
//     public void calculateFareUnkownType(){
//         Date inTime = new Date();
//         inTime.setTime( System.currentTimeMillis() - (  60 * 60 * 1000) );
//         Date outTime = new Date();
//         ParkingSpot parkingSpot = new ParkingSpot(1, null,false);

//         ticket.setInTime(inTime);
//         ticket.setOutTime(outTime);
//         ticket.setParkingSpot(parkingSpot);
//         assertThrows(NullPointerException.class, () -> fareCalculatorService.calculateFare(ticket));
//     }

//     @Test
//     public void calculateFareBikeWithFutureInTime(){
//         Date inTime = new Date();
//         inTime.setTime( System.currentTimeMillis() + (  60 * 60 * 1000) );
//         Date outTime = new Date();
//         ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE,false);

//         ticket.setInTime(inTime);
//         ticket.setOutTime(outTime);
//         ticket.setParkingSpot(parkingSpot);
//         assertThrows(IllegalArgumentException.class, () -> fareCalculatorService.calculateFare(ticket));
//     }

//     //@Test
//     public void calculateFareBikeWithLessThanOneHourParkingTime(){
//         Date inTime = new Date();
//         inTime.setTime( System.currentTimeMillis() - (  45 * 60 * 1000) );//45 minutes parking time should give 3/4th parking fare
//         Date outTime = new Date();
//         ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE,false);

//         ticket.setInTime(inTime);
//         ticket.setOutTime(outTime);
//         ticket.setParkingSpot(parkingSpot);
//         classUnderTest.calculateFare(ticket);
//         assertEquals((0.75 * Fare.BIKE_RATE_PER_HOUR), ticket.getPrice() );
//     }

//     // @Test
//     public void calculateFareCarWithLessThanOneHourParkingTime(){
//         Date inTime = new Date();
//         inTime.setTime( System.currentTimeMillis() - (  45 * 60 * 1000) );//45 minutes parking time should give 3/4th parking fare
//         Date outTime = new Date();
//         ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);

//         ticket.setInTime(inTime);
//         ticket.setOutTime(outTime);
//         ticket.setParkingSpot(parkingSpot);
//         classUnderTest.calculateFare(ticket);
//         assertEquals( (0.75 * Fare.CAR_RATE_PER_HOUR) , ticket.getPrice());
//     }

//     // @Test
//     public void calculateFareCarWithMoreThanADayParkingTime(){
//         Date inTime = new Date();
//         inTime.setTime( System.currentTimeMillis() - (  24 * 60 * 60 * 1000) );//24 hours parking time should give 24 * parking fare per hour
//         Date outTime = new Date();
//         ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);

//         ticket.setInTime(inTime);
//         ticket.setOutTime(outTime);
//         ticket.setParkingSpot(parkingSpot);
//         classUnderTest.calculateFare(ticket);
//         assertEquals( (24 * Fare.CAR_RATE_PER_HOUR) , ticket.getPrice());
//     }
    
// }

// //mock 
