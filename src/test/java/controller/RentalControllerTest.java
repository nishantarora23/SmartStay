package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import model.Apartment;
import model.Condo;
import model.DatabaseUtility;
import model.House;
import model.Lease;
import model.Payment;
import model.Property;
import model.Tenant;

@RunWith(MockitoJUnitRunner.class)
class RentalControllerTest {
	
//	private RentalController controller = new RentalController();
//	private Tenant tenant1 = new Tenant("Rohit", "Singh", "rohit.singh@gmail.com", "555-8861-234");
//    private Tenant tenant2 = new Tenant("Ram", "Patel", "ram.patel@gmail.com", "555-8871-678");
//    
//	@Test
//    public void testAddTenant() {
//        // To test that tenant is added to Tenant's list of tenants
//        rentalController.addTenant(tenant1);
//        assertTrue(rentalController.getAllTenants().contains(tenant1));
//
//        // To test that multiple tenants can be added to the tenant
//        rentalController.addTenant(tenant2);
//        assertTrue(rentalController.getAllTenants().contains(tenant2));
//
//        // To test that tenant IDs are assigned correctly
//        assertEquals(1, tenant1.getTenantID());
//        assertEquals(2, tenant2.getTenantID());
//    }
//	
//	@Test
//	public void testGetAllTenants() {
//		ArrayList<Tenant> tenants = new ArrayList<>();
//		tenants.add(tenant1);
//		tenants.add(tenant2);
//		
//		rentalController.addTenant(tenant1);
//		rentalController.addTenant(tenant2);
//		
//		// To test that getAllTenants returns the correct list of tenants
//        assertEquals(tenants.get(0).getTenantID(), rentalController.getAllTenants().get(0).getTenantID());
//        
//	}
	
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private RentalController rentalController;
	
	@BeforeEach
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	    System.setOut(new PrintStream(outputStreamCaptor));
	    Field instance = DatabaseUtility.class.getDeclaredField("instance");
		instance.setAccessible(true);
	    instance.set(null, null);
	    rentalController = new RentalController();
	}
	
	@Test
	public void testMakeRentPaymentSuccessful() {
		Tenant mockTenant = mock(Tenant.class);
		Apartment mockApartment = mock(Apartment.class);
		Lease lease = new Lease(mockTenant, mockApartment, LocalDate.parse("2023-04-01"), LocalDate.parse("2024-03-31"), 12000);
		assertEquals(0, lease.getAllPayments().size());
		rentalController.addLease(lease);
        rentalController.makeRentPayment(1, 1000);
        assertEquals(1, lease.getAllPayments().size());
        assertEquals(1000.0, lease.getAllPayments().get(0).getAmountPaid());
        assertEquals("Your rent payment is successful!", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testMakeRentPaymentNoLeaseFound() {
		Tenant mockTenant = mock(Tenant.class);
		Apartment mockApartment = mock(Apartment.class);
		Lease lease = new Lease(mockTenant, mockApartment, LocalDate.parse("2023-02-01"), LocalDate.parse("2023-04-01"), 12000);
		assertEquals(0, lease.getAllPayments().size());
		rentalController.addLease(lease);
        rentalController.makeRentPayment(3, 1000);
        assertEquals("No active lease found.", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testDisplayRentSummary() {
		Tenant paidMockTenant = mock(Tenant.class);
		when(paidMockTenant.getFirstName()).thenReturn("John");
		when(paidMockTenant.getLastName()).thenReturn("Doe");
		Apartment paidMockApartment = mock(Apartment.class);
		when(paidMockApartment.getFullAddress()).thenReturn("Apartment 123, ABC Street, Montreal, QC, Canada, H3G 1M8");
		
		Lease paidLease = new Lease(paidMockTenant, paidMockApartment, LocalDate.parse("2023-04-01"), LocalDate.parse("2024-03-31"), 12000);
		paidLease.setRentDue(0);
		
		Tenant unpaidMockTenant = mock(Tenant.class);
		when(unpaidMockTenant.getFirstName()).thenReturn("Jenny");
		when(unpaidMockTenant.getLastName()).thenReturn("Doe");
		Apartment unpaidMockApartment = mock(Apartment.class);
		when(unpaidMockApartment.getFullAddress()).thenReturn("Apartment 456, XYZ Street, Montreal, QC, Canada, H3G 1M8");
		Lease unpaidLease = new Lease(unpaidMockTenant, unpaidMockApartment, LocalDate.parse("2023-04-01"), LocalDate.parse("2024-03-31"), 12000);
		
		rentalController.addLease(paidLease);
		rentalController.addLease(unpaidLease);
		
		HashMap<String, ArrayList<String>> map = rentalController.displayRentPaymentSummary();
		assertEquals(2, map.size());
		assertEquals("John Doe: Apartment 123, ABC Street, Montreal, QC, Canada, H3G 1M8", map.get("PAID").get(0));
		assertEquals("Jenny Doe: Apartment 456, XYZ Street, Montreal, QC, Canada, H3G 1M8", map.get("UNPAID").get(0));
	}
	
	@Test
	public void testNotifyInterestedTenants() {
		Tenant mockExistingTenant = mock(Tenant.class);
		Tenant mockInterestedTenant = mock(Tenant.class);
		when(mockInterestedTenant.getFirstName()).thenReturn("Jenny");
		when(mockInterestedTenant.getLastName()).thenReturn("Doe");
		Apartment mockApartment = mock(Apartment.class);
		when(mockApartment.getFullAddress()).thenReturn("Apartment 123, ABC Street, Montreal, QC, Canada, H3G 1M8");
		ArrayList<Tenant> interestedTenants = new ArrayList<Tenant>();
		interestedTenants.add(mockInterestedTenant);
		when(mockApartment.getAllInterestedTenants()).thenReturn(interestedTenants);
		Lease lease = new Lease(mockExistingTenant, mockApartment, LocalDate.parse("2023-02-01"), LocalDate.parse("2023-02-28"), 12000);
		
		rentalController.addLease(lease);
		HashMap<String, ArrayList<String>> map = rentalController.notifyInterestedTenants();
		assertEquals(1, map.size());
		assertEquals("Jenny Doe", map.get(lease.getProperty().getFullAddress()).get(0));
	}
}
