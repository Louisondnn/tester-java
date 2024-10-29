// import com.parkit.parkingsystem.dao.ParkingSpotDAO;
// import com.parkit.parkingsystem.dao.TicketDAO;
// import com.parkit.parkingsystem.service.InteractiveShell;
// import com.parkit.parkingsystem.service.ParkingService;
// import com.parkit.parkingsystem.util.InputReaderUtil;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import static org.mockito.Mockito.*;

// public class InteractiveShellTest {

//     @Mock
//     private InputReaderUtil inputReaderUtil;

//     @Mock
//    private ParkingSpotDAO parkingSpotDAO;

//     @Mock
//     private TicketDAO ticketDAO;
// //  @Mock
//     private ParkingService parkingService;

//     @InjectMocks
//     private InteractiveShell interactiveShell;

//     @BeforeEach
//     public void setup() {
//         //  mocks
//     }

//     @Test
//     public void testLoadInterface() {
//         InteractiveShell.loadInterface();
//         verify(inputReaderUtil, times(1)).readSelection();
//     }

//     @Test
//     public void testLoadMenu() {
//         InteractiveShell.loadMenu();
//     }

//     @Test
//     public void testProcessIncomingVehicle() {
//         when(inputReaderUtil.readSelection()).thenReturn(1);
//         InteractiveShell.loadInterface();
//         verify(parkingService, times(1)).processIncomingVehicle();
//     }

//     @Test
//     public void testProcessExitingVehicle() {
//         when(inputReaderUtil.readSelection()).thenReturn(2);
//         InteractiveShell.loadInterface();
//         verify(parkingService, times(1)).processExitingVehicle();
//     }

//     @Test
//     public void testShutdownSystem() {
//         when(inputReaderUtil.readSelection()).thenReturn(3);
//         InteractiveShell.loadInterface();
//     }

//     @Test
//     public void testUnsupportedOption() {
//         when(inputReaderUtil.readSelection()).thenReturn(4);
//         InteractiveShell.loadInterface();
//     }
// }