package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() throws Exception{
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
        dataBasePrepareService.clearDataBaseEntries();
    }

    @AfterAll
    private static void tearDown(){

    }
    // completer les 2 classes 
@Test
public void testParkingACar() throws Exception {
    // Arrange

    ParkingService parkingService = new ParkingService(null, null, null);
    int availableParking = parkingService.getAvailableParking(); // Directly call the method
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
    // when(((ParkingService) parkingSpotDAO).getAvailableParking()).thenReturn(new ParkingSpot(1, ParkingType.CAR));

    // Act
    Ticket ticket = parkingService.processIncomingVehicle();

    // Assert
    assertNotNull(ticket);
    assertEquals("ABCDEF", ticket.getVehicleRegNumber());
    assertEquals(1, ticket.getParkingSpot().getId());
}

@Test
public void testParkingLotExit() throws Exception {
    // Arrange
    testParkingACar(); // Ensure a car is parked first
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
    // when(ticketDAO.getTicket("ABCDEF")).thenReturn(new Ticket("ABCDEF", new ParkingSpot(1, ParkingType.CAR, false)));

    // Act
    parkingService.processExitingVehicle();

    // Assert
    verify(ticketDAO, times(1)).deleteTicket("ABCDEF");
    verify(parkingSpotDAO, times(1)).freeParkingSpot(1);
}

}
