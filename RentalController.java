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
		// Check if property is available
		boolean propertyAvailable = true;
		for (Lease lease : leases) {
			if (lease.getProperty() == property && !lease.isExpired()) {
				propertyAvailable = false;
				break;
			}
		}

		if (propertyAvailable) {
			Lease lease = new Lease(tenant, property, startDate, endDate, rentAmount);
			leases.add(lease);
		} else {
			// Property is already rented
			System.out.println("Sorry, the property is not available for rent.");
		}
	}

	public ArrayList<Lease> getRentedUnits() {
		ArrayList<Lease> rentedUnits = new ArrayList<>();
		for (Lease lease : leases) {
			if (!lease.isExpired()) {
				rentedUnits.add(lease);
			}
		}
		return rentedUnits;
	}

	public ArrayList<Property> getVacantUnits() {
		ArrayList<Property> vacantUnits = new ArrayList<>();
		for (Property property : properties) {
			boolean propertyAvailable = true;
			for (Lease lease : leases) {
				if (lease.getProperty() == property && !lease.isExpired()) {
					propertyAvailable = false;
					break;
				}
			}
			if (propertyAvailable) {
				vacantUnits.add(property);
			}
		}
		return vacantUnits;
	}
	
	public Tenant getTenant(int tenantID) {
	    for (Tenant tenant : tenants) {
	        if (tenant.getTenantID() == tenantID) {
	            return tenant;
	        }
	    }
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
