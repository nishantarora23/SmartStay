package controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import model.Tenant;

class RentalControllerTest {
	private RentalController controller = new RentalController();
	private Tenant tenant1 = new Tenant("Rohit", "Singh", "rohit.singh@gmail.com", "555-8861-234");
    private Tenant tenant2 = new Tenant("Ram", "Patel", "ram.patel@gmail.com", "555-8871-678");
    
	@Test
    public void testAddTenant() {
        // To test that tenant is added to Tenant's list of tenants
        controller.addTenant(tenant1);
        assertTrue(controller.getAllTenants().contains(tenant1));

        // To test that multiple tenants can be added to the tenant
        controller.addTenant(tenant2);
        assertTrue(controller.getAllTenants().contains(tenant2));

        // To test that tenant IDs are assigned correctly
        assertEquals(1, tenant1.getTenantID());
        assertEquals(2, tenant2.getTenantID());
    }
	
	@Test
	public void testGetAllTenants() {
		ArrayList<Tenant> tenants = new ArrayList<>();
		tenants.add(tenant1);
		tenants.add(tenant2);
		
		controller.addTenant(tenant1);
		controller.addTenant(tenant2);
		
		// To test that getAllTenants returns the correct list of tenants
        assertEquals(tenants.get(0).getTenantID(), controller.getAllTenants().get(0).getTenantID());
        
	}

}
