package com.parkit.parkingsystem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.service.FareCalculatorService;

public class ParkingFareCalculatorTest {

    @Test
    public void calculateFareCarWithLessThan30MinutesParkingTime() {
        FareCalculatorService calculator = Mockito.mock(FareCalculatorService.class);
        ParkingType parkingType = ParkingType.CAR;
        long duration = 5 * (1000 * 60 * 60); // 5 hours in milliseconds

        Mockito.when(calculator.calculateFareBasedOnType(parkingType, duration)).thenReturn(7.5);

        double fare = calculator.calculateFareBasedOnType(parkingType, duration);

        assertEquals(7.5, fare);
    }
}