import java.time.LocalDate;
import java.util.ArrayList;

class RentalController {
	
	private ArrayList<Property> properties;
	private ArrayList<Tenant> tenants;
	private ArrayList<Lease> leases;
	
	public RentalController() {
		this.properties = new ArrayList<>();
		this.tenants = new ArrayList<>();
		this.leases = new ArrayList<>();
	}

	public void addProperty(Property property) {
		properties.add(property);
	}

	public void addTenant(Tenant tenant) {
		tenants.add(tenant);
	}
	
	public ArrayList<Property> getAllProperties() {
		return properties;
	}

	public ArrayList<Tenant> getAllTenants() {
		return tenants;
	}
	
	public ArrayList<Lease> getAllLeases() {
		return leases;
	}

	public void addLease(Lease lease) {
	    leases.add(lease);
	}

	public void rentUnit(Property property, Tenant tenant, LocalDate startDate, LocalDate endDate, double rentAmount) {
		//TODO
	}

	public ArrayList<Lease> getRentedUnits() {
		//TODO
		return null;
	}

	public ArrayList<Property> getVacantUnits() {
		//TODO
		return null;
	}
	
	public Tenant getTenant(int tenantID) {
	    //TODO
	    return null;
	}

	public void notifyInterestedTenants(){
		for(Lease lease : leases) {
			if(lease.isExpired()) {
				Property property = lease.getProperty();
				ArrayList<Tenant> interestedTenants = property.getAllInterestedTenants();
				if(!interestedTenants.isEmpty()) {
					System.out.println("The unit at " + property.getCivicAddress()  + " is available to rent. Interested tenants: ");
		            for (Tenant tenant : interestedTenants) {
		                System.out.println("Tenant Information: " + tenant.toString());
		            }
				}
			}
		}
	}
	
	public void displayRentPaymentSummary(){
		//TODO
	}
}
