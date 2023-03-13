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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import model.Apartment;
import model.DatabaseUtility;
import model.Lease;
import model.Property;
import model.Tenant;

@RunWith(MockitoJUnitRunner.class)
class RentalControllerTest {

	@Mock
	private Property mockedProperty = mock(Property.class);
	@Mock
	private Tenant mockedTenant = mock(Tenant.class);
	@Mock
	private Lease mockedLease = mock(Lease.class);

	private RentalController rentalController;

	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		System.setOut(new PrintStream(outputStream));
		Field instance = DatabaseUtility.class.getDeclaredField("instance");
		instance.setAccessible(true);
		instance.set(null, null);
		rentalController = new RentalController();
	}

	@Test
	public void testMakeRentPaymentSuccessful() {
		
		//mock the behaviour of a tenant and an apartment to create a lease for which the rent has to be paid
		Tenant mockTenant = mock(Tenant.class);
		Apartment mockApartment = mock(Apartment.class);
		Lease lease = new Lease(mockTenant, mockApartment, LocalDate.parse("2023-04-01"), LocalDate.parse("2024-03-31"),
				12000);
		assertEquals(0, lease.getAllPayments().size());
		rentalController.addLease(lease);
		
		// calling test method
		rentalController.makeRentPayment(1, 1000);
		
		//verify that rent was paid successfully
		assertEquals(1, lease.getAllPayments().size());
		assertEquals(1000.0, lease.getAllPayments().get(0).getAmountPaid());
		assertEquals("Your rent payment is successful!", outputStream.toString().trim());
	}

	@Test
	public void testMakeRentPaymentNoLeaseFound() {
		//mock the behaviour of a tenant and an apartment to create a lease for which the rent has to be paid
		Tenant mockTenant = mock(Tenant.class);
		Apartment mockApartment = mock(Apartment.class);
		Lease lease = new Lease(mockTenant, mockApartment, LocalDate.parse("2023-02-01"), LocalDate.parse("2023-04-01"),
				12000);
		assertEquals(0, lease.getAllPayments().size());
		rentalController.addLease(lease);
		
		// calling test method
		rentalController.makeRentPayment(3, 1000);
		
		//verify rent cannot be paid for a lease that does not exist
		assertEquals("No active lease found.", outputStream.toString().trim());
	}

	@Test
	public void testDisplayRentSummary() {
		
		//mock the behaviour of a tenant and an apartment to create a lease for which rent has been paid
		Tenant paidMockTenant = mock(Tenant.class);
		when(paidMockTenant.getFirstName()).thenReturn("John");
		when(paidMockTenant.getLastName()).thenReturn("Doe");
		Apartment paidMockApartment = mock(Apartment.class);
		when(paidMockApartment.getFullAddress()).thenReturn("Apartment 123, ABC Street, Montreal, QC, Canada, H3G 1M8");

		Lease paidLease = new Lease(paidMockTenant, paidMockApartment, LocalDate.parse("2023-04-01"),
				LocalDate.parse("2024-03-31"), 12000);
		paidLease.setRentDue(0);

		//mock a tenant and an apartment to create a lease for which rent has been paid
		Tenant unpaidMockTenant = mock(Tenant.class);
		when(unpaidMockTenant.getFirstName()).thenReturn("Jenny");
		when(unpaidMockTenant.getLastName()).thenReturn("Doe");
		Apartment unpaidMockApartment = mock(Apartment.class);
		when(unpaidMockApartment.getFullAddress())
		.thenReturn("Apartment 456, XYZ Street, Montreal, QC, Canada, H3G 1M8");
		Lease unpaidLease = new Lease(unpaidMockTenant, unpaidMockApartment, LocalDate.parse("2023-04-01"),
				LocalDate.parse("2024-03-31"), 12000);

		rentalController.addLease(paidLease);
		rentalController.addLease(unpaidLease);
		
		//calling test method
		HashMap<String, ArrayList<String>> map = rentalController.displayRentPaymentSummary();
		
		//verify that tenants who paid the rent and who did not pay the rent are listed
		assertEquals(2, map.size());
		assertEquals("John Doe: Apartment 123, ABC Street, Montreal, QC, Canada, H3G 1M8", map.get("PAID").get(0));
		assertEquals("Jenny Doe: Apartment 456, XYZ Street, Montreal, QC, Canada, H3G 1M8", map.get("UNPAID").get(0));
	}

	@Test
	public void testNotifyInterestedTenants() {
		//mock the behaviour of an existing tenant and an apartment to create a lease for a property having an interested tenant
		Tenant mockExistingTenant = mock(Tenant.class);
		Tenant mockInterestedTenant = mock(Tenant.class);
		when(mockInterestedTenant.getFirstName()).thenReturn("Jenny");
		when(mockInterestedTenant.getLastName()).thenReturn("Doe");
		Apartment mockApartment = mock(Apartment.class);
		when(mockApartment.getFullAddress()).thenReturn("Apartment 123, ABC Street, Montreal, QC, Canada, H3G 1M8");
		ArrayList<Tenant> interestedTenants = new ArrayList<Tenant>();
		interestedTenants.add(mockInterestedTenant);
		when(mockApartment.getAllInterestedTenants()).thenReturn(interestedTenants);
		Lease lease = new Lease(mockExistingTenant, mockApartment, LocalDate.parse("2023-02-01"),
				LocalDate.parse("2023-02-28"), 12000);

		rentalController.addLease(lease);
		
		//calling test method
		HashMap<String, ArrayList<String>> map = rentalController.notifyInterestedTenants();
		
		//verify interested tenants are notified
		assertEquals(1, map.size());
		assertEquals("Jenny Doe", map.get(lease.getProperty().getFullAddress()).get(0));
	}

	@Test
	public void testAddProperty() {
		rentalController.addProperty(mockedProperty);
		ArrayList<Property> properties = rentalController.getAllProperties();
		assertEquals(1, properties.size());
		assertEquals(mockedProperty, properties.get(0));
	}

	@Test
	public void testAddLease() {
		rentalController.addLease(mockedLease);
		ArrayList<Lease> leases = rentalController.getAllLeases();
		assertEquals(1, leases.size());
		assertEquals(mockedLease, leases.get(0));
	}

	@Test
	public void testGetAllProperties() {
		ArrayList<Property> properties = rentalController.getAllProperties();
		assertEquals(0, properties.size());
	}

	@Test
	public void testGetAllLeases() {
		ArrayList<Lease> leases = rentalController.getAllLeases();
		assertEquals(0, leases.size());
	}

	@Test
	public void testRentUnitWhenPropertyIsNotAvailable() {

		// Mock the behavior of getAllLeases() to return a list with a non-expired lease
		// for the same property
		when(mockedLease.isExpired()).thenReturn(false);
		when(mockedLease.getProperty()).thenReturn(mockedProperty);
		ArrayList<Lease> leases = new ArrayList<>();
		leases.add(mockedLease);
		RentalController rentalController = mock(RentalController.class);
		when(rentalController.getLeases()).thenReturn(leases);
		int noOFLeaseAlreadyPresent = rentalController.getLeases().size();

		// calling test method
		rentalController.rentUnit(mockedProperty, mockedTenant, LocalDate.now(), LocalDate.now(), 100.0);

		// Verify that no new lease was added to the list of leases
		assertEquals(noOFLeaseAlreadyPresent, rentalController.getLeases().size());
	}

	@Test
	public void testRentUnitWhenPropertyIsAvailable() {

		// Mock the behavior of getAllLeases() to return an empty list
		when(mockedLease.isExpired()).thenReturn(false);
		ArrayList<Lease> leases1 = new ArrayList<>();
		leases1.add(mockedLease);
		rentalController.setLeases(leases1);
		int noOFLeaseAlreadyPresent = rentalController.getLeases().size();

		// calling test method
		rentalController.rentUnit(mockedProperty, mockedTenant, LocalDate.now(), LocalDate.now(), 100.0);

		// Verify that a new lease was added to the list of leases
		assertEquals(noOFLeaseAlreadyPresent + 1, rentalController.getLeases().size());
	}

	@Test
	public void testGetRentedUnits() {

		// Mock the behavior of getAllLeases() to return a list with a non-expired lease
		when(mockedLease.isExpired()).thenReturn(false);
		ArrayList<Lease> leases = new ArrayList<>();
		leases.add(mockedLease);
		rentalController.setLeases(leases);

		// calling test method
		ArrayList<Lease> rentedUnits = rentalController.getRentedUnits();

		// Verify that the returned list contains the non-expired lease
		assertEquals(1, rentedUnits.size());
	}

	@Test
	public void testAddTenant() {
		// setup
		Tenant mockedTenant1 = mock(Tenant.class);
		Tenant mockedTenant2 = mock(Tenant.class);

		// calling test method
		rentalController.addTenant(mockedTenant1);
		assertTrue(rentalController.getAllTenants().contains(mockedTenant1));

		// To test that multiple tenants can be added to the tenant
		rentalController.addTenant(mockedTenant2);
		assertTrue(rentalController.getAllTenants().contains(mockedTenant2));

	}

	@Test
	public void testGetAllTenants() {
		// setup
		Tenant mockedTenant1 = mock(Tenant.class);
		when(mockedTenant1.getTenantID()).thenReturn(123);

		ArrayList<Tenant> tenants = new ArrayList<>();
		tenants.add(mockedTenant1);
		RentalController rentalController = new RentalController();
		rentalController.addTenant(mockedTenant1);

		// calling test method and verifying
		// To test that getAllTenants returns the correct list of tenants
		assertEquals(tenants.get(0).getTenantID(), rentalController.getAllTenants().get(0).getTenantID());

	}

}
