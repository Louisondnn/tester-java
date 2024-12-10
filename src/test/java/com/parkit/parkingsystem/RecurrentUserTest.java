package com.parkit.parkingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.parkit.parkingsystem.service.RecurrentUser ;

public class RecurrentUserTest {

    private RecurrentUser  recurrentUser ;
    private final double normalTariff = 10.0; // normal tariff

    @BeforeEach
    public void setUp() {
        recurrentUser  = new RecurrentUser (normalTariff);
    }

    @Test
    public void enterGarageAsNewUser () {
        String licensePlate = "ABC123";

        recurrentUser .enterGarage(licensePlate);

        assertFalse(recurrentUser .calculateTariff(licensePlate) < normalTariff); 
    }

    @Test
    public void enterGarageAsRecurrentUser () {
        String licensePlate = "XYZ789";

        recurrentUser .enterGarage(licensePlate);
        recurrentUser .enterGarage(licensePlate);

        assertTrue(recurrentUser .calculateTariff(licensePlate) < normalTariff); 
    }

    @Test
    public void calculateTariffForRecurrentUser () {
        String licensePlate = "DEF456";


        recurrentUser .enterGarage(licensePlate);
        recurrentUser .enterGarage(licensePlate);

        double tariff = recurrentUser .calculateTariff(licensePlate);

        assertEquals(normalTariff * 0.95, tariff); // Should be 5% discount
    }

    @Test
    public void exitGarageForRecurrentUser () {
        String licensePlate = "GHI789";

        recurrentUser .enterGarage(licensePlate);
        recurrentUser .enterGarage(licensePlate);
        
        recurrentUser .exitGarage(licensePlate);

        assertTrue(recurrentUser .calculateTariff(licensePlate) < normalTariff);    }
}