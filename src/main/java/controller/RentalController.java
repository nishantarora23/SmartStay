package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import model.Lease;
import model.Payment;
import model.Property;
import model.Tenant;

public class RentalController {
	
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

	/**
	 * This method returns a hashmap containing the property and the interested tenants
	 * @return propertyAndInterestedTenants
	 */
	public HashMap<String, ArrayList<String>> notifyInterestedTenants() {
		HashMap<String, ArrayList<String>> propertyAndInterestedTenants  = new HashMap<String, ArrayList<String>>();
		for(Lease lease : leases) {
			if(lease.isExpired()) {
				Property property = lease.getProperty();
				ArrayList<Tenant> interestedTenants = property.getAllInterestedTenants();
				if(!interestedTenants.isEmpty()) {
					ArrayList<String> interestedTenantNames = new ArrayList<String>();
		            for (Tenant tenant : interestedTenants) {
		                interestedTenantNames.add(tenant.getFirstName() + " " + tenant.getLastName());
		            }
		            propertyAndInterestedTenants.put(property.getFullAddress(), interestedTenantNames);
				}
			}
		}
		return propertyAndInterestedTenants;
	}
	
	/**
	 * This method returns whether rent payment was successful or not
	 * @param leaseID
	 * @param amountPaid
	 * @return true or false
	 */
	public boolean makeRentPayment(int leaseID, double amountPaid) {
		for(Lease lease : leases) {
			if(lease.getLeaseID() == leaseID) {
				Payment payment = new Payment(amountPaid, LocalDate.now());
				if(lease.getRentDue() - amountPaid < 0)
					lease.setRentDue(0);
				else lease.setRentDue(lease.getRentDue() - amountPaid);
				lease.addPayment(payment);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns the tenants who paid the rent and who did not
	 * @return paidOrNotPaidAndTenants
	 */
	public HashMap<String, ArrayList<String>> displayRentPaymentSummary() {
		ArrayList<String> paidTenants = new ArrayList<String>();
		ArrayList<String> unpaidTenants = new ArrayList<String>();
		for(Lease lease : leases) {
			if(lease.getRentDue() > 0) 
				unpaidTenants.add(lease.getTenant().getFirstName() + " " + lease.getTenant().getLastName());
			else paidTenants.add(lease.getTenant().getFirstName() + " " + lease.getTenant().getLastName());
		}
		
		HashMap<String, ArrayList<String>> paidOrNotPaidAndTenants = new HashMap<String, ArrayList<String>>();
		paidOrNotPaidAndTenants.put("PAID", paidTenants);
		paidOrNotPaidAndTenants.put("UNPAID", unpaidTenants);
		return paidOrNotPaidAndTenants;
	}
}
