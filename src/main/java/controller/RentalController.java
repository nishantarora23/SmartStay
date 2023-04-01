package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import model.DatabaseUtility;
import model.Lease;
import model.Payment;
import model.Property;
import model.Tenant;

/**
 * The RentalController class represents the controller of the SmartStay (rental
 * management system). It handles the business logic for properties, tenants,
 * and leases. It interacts with the model and the view.
 */
public class RentalController {

	private ArrayList<Property> properties;
	private ArrayList<Tenant> tenants;
	private ArrayList<Lease> leases;
	private ArrayList<Lease> expiredLeases;
	private DatabaseUtility databaseUtility;

	/**
	 * A constructor that gets the instances of tenants, properties and leases from
	 * the DatabaseUtility singleton class
	 */
	public RentalController() {
		DatabaseUtility databaseUtility = getDatabaseUtility();
		setTenants(databaseUtility.getTenants());
		setProperties(databaseUtility.getProperties());
		setLeases(databaseUtility.getLeases());
		setExpiredLeases(databaseUtility.getExpiredLeases());
	}

	public void setDatabaseUtility(DatabaseUtility databaseUtility) {
		this.databaseUtility = databaseUtility;
	}

	protected DatabaseUtility getDatabaseUtility() {
		return DatabaseUtility.getInstance();
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	public ArrayList<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(ArrayList<Tenant> tenants) {
		this.tenants = tenants;
	}

	public ArrayList<Lease> getLeases() {
		return leases;
	}

	public void setLeases(ArrayList<Lease> leases) {
		this.leases = leases;
	}

	public ArrayList<Lease> getExpiredLeases() {
		return expiredLeases;
	}

	public void setExpiredLeases(ArrayList<Lease> expiredLeases) {
		this.expiredLeases = expiredLeases;
	}

	/**
	 * Adds a property to the list of properties
	 * 
	 * @param property
	 */
	public void addProperty(Property property) {
		properties.add(property);
	}

	/**
	 * Adds a tenant to the list of tenants
	 * 
	 * @param tenant
	 */
	public void addTenant(Tenant tenant) {
		tenants.add(tenant);
	}

	/**
	 * Adds a lease to the leases
	 * 
	 * @param lease
	 */
	public void addLease(Lease lease) {
		leases.add(lease);
	}

	/**
	 * Returns all the properties in the list
	 * 
	 * @return properties
	 */
	public ArrayList<Property> getAllProperties() {
		return properties;
	}

	/**
	 * Returns all the tenants in the list
	 * 
	 * @return tenants
	 */
	public ArrayList<Tenant> getAllTenants() {
		return tenants;
	}

	/**
	 * Returns all the leases in the list
	 * 
	 * @return leases
	 */
	public ArrayList<Lease> getAllLeases() {
		return leases;
	}

	/**
	 * This method rents a unit based on the availability of the property. If the
	 * property is available, a lease is created between the tenant and the
	 * property, otherwise a message is displayed stating that property is not
	 * available.
	 * 
	 * @param property
	 * @param tenant
	 * @param startDate
	 * @param endDate
	 * @param rentAmount
	 */
	public boolean rentUnit(Property property, Tenant tenant, LocalDate startDate, LocalDate endDate, double rentAmount) {
		// Check if property is available
		boolean propertyAvailable = true;
		ArrayList<Lease> leases = this.getLeases();
		for (Lease lease : leases) {
			if (lease.getProperty() == property && !lease.isExpired()) {
				propertyAvailable = false;
				break;
			}
		}

		if (propertyAvailable) {
			Lease lease = new Lease(tenant, property, startDate, endDate, rentAmount);
			this.leases.add(lease);
			return true;
		} else {
			// Property is already rented
			System.out.println("Sorry, the property is not available for rent.");
			return false;
		}
	}

	/**
	 * This method returns the rented units
	 * 
	 * @return rentedUnits
	 */
	public ArrayList<Lease> getRentedUnits() {
		ArrayList<Lease> rentedUnits = new ArrayList<>();
		ArrayList<Lease> leases = this.getLeases();
		for (Lease lease : leases) {
			if (!lease.isExpired()) {
				rentedUnits.add(lease);
			}
		}
		return rentedUnits;
	}

	/**
	 * This method returns the units that are available
	 * 
	 * @return
	 */
	public ArrayList<Property> getVacantUnits() {
		ArrayList<Property> vacantUnits = new ArrayList<>();
		for (Property property : getProperties()) {
			boolean propertyAvailable = true;
			for (Lease lease : getLeases()) {
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

	/**
	 * This method returns the tenant based on the tenant ID
	 * 
	 * @param tenantID
	 * @return
	 */
	public Tenant getTenant(int tenantID) {
		for (Tenant tenant : getTenants()) {
			if (tenant.getTenantID() == tenantID) {
				return tenant;
			}
		}
		return null;
	}

	/**
	 * This method returns a hashmap containing the property and its interested
	 * tenants when the property becomes available
	 * 
	 * @return propertyAndInterestedTenants
	 */
	public HashMap<String, ArrayList<String>> notifyInterestedTenants() {
		HashMap<String, ArrayList<String>> propertyAndInterestedTenants = new HashMap<String, ArrayList<String>>();
		ArrayList<Lease> expiredTempLeases = new ArrayList<Lease>();
		for (Lease lease : getLeases()) {
			if (lease.isExpired()) {
				Property property = lease.getProperty();
				ArrayList<Tenant> interestedTenants = property.getAllInterestedTenants();
				if (!interestedTenants.isEmpty()) {
					ArrayList<String> interestedTenantNames = new ArrayList<String>();
					for (Tenant tenant : interestedTenants) {
						interestedTenantNames.add(tenant.getFirstName() + " " + tenant.getLastName());
					}
					propertyAndInterestedTenants.put(property.getFullAddress(), interestedTenantNames);
				}
				// adding lease to expired lease list so that current lease are always active
				// leases
				expiredTempLeases.add(lease);
			}
		}
		// removing expired lease from lease list
		leases.removeAll(expiredTempLeases);

		return propertyAndInterestedTenants;
	}

	/**
	 * This method allows user to pay their rent
	 * 
	 * @param leaseID
	 * @param amountPaid
	 */
	public boolean makeRentPayment(int leaseID, double amountPaid) {
		boolean leaseFound = false;
		for (Lease lease : leases) {
			if (lease.getLeaseID() == leaseID && !lease.isExpired()) {
				Payment payment = new Payment(amountPaid, LocalDate.now());
				lease.addPayment(payment);
				if (lease.getRentDue() - amountPaid < 0) {
					lease.setRentDue(0);
				} else {
					lease.setRentDue(lease.getRentDue() - amountPaid);
//					System.out.println("Your rent payment is successful!");
				}
				leaseFound = true;
				return leaseFound;
			}
		}
//		if (!leaseFound)
//			System.out.println("No active lease found.");
		return leaseFound;
	}

	/**
	 * This method returns the tenants who paid the rent and who did not
	 * 
	 * @return paidOrNotPaidAndTenants
	 */
	public HashMap<String, ArrayList<String>> displayRentPaymentSummary() {
		ArrayList<String> paidTenants = new ArrayList<String>();
		ArrayList<String> unpaidTenants = new ArrayList<String>();
		for (Lease lease : leases) {
			if (!lease.isExpired()) {
				if (lease.getRentDue() > 0)
					unpaidTenants.add(lease.getTenant().getFirstName() + " " + lease.getTenant().getLastName() + ": "
							+ lease.getProperty().getFullAddress());
				else
					paidTenants.add(lease.getTenant().getFirstName() + " " + lease.getTenant().getLastName() + ": "
							+ lease.getProperty().getFullAddress());
			}
		}

		HashMap<String, ArrayList<String>> paidOrNotPaidAndTenants = new HashMap<String, ArrayList<String>>();
		if (!paidTenants.isEmpty())
			paidOrNotPaidAndTenants.put("PAID", paidTenants);
		if (!unpaidTenants.isEmpty())
			paidOrNotPaidAndTenants.put("UNPAID", unpaidTenants);
		return paidOrNotPaidAndTenants;
	}

	/**
	 * Retrieves a lease object that matches the specified property and date range.
	 * 
	 * @param property  The property object to match against.
	 * @param startDate The start date of the date range to match against.
	 * @param endDate   The end date of the date range to match against.
	 * @return A lease object that matches the specified property and date range, or
	 *         null if no match is found.
	 */

	public Lease getLeaseByPropertyAndDates(Property property, LocalDate startDate, LocalDate endDate) {
		// Get all leases from the repository
		ArrayList<Lease> leases = getAllLeases();

		// Iterate through all leases and return the first one that matches the
		// specified criteria
		for (Lease lease : leases) {
			if (lease.getProperty().getPropertyId() == property.getPropertyId() && lease.getEndDate().isAfter(startDate)
					&& lease.getStartDate().isBefore(endDate)) {
				return lease;
			}
		}
		return null;
	}
}
