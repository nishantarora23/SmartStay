package model;

import java.util.ArrayList;

/**
 * 
 * The DatabaseUtility class represents a utility class for managing the
 * database
 * 
 * that stores the properties, tenants, leases, and expired leases.
 */
public class DatabaseUtility {

	private static DatabaseUtility instance;
	private ArrayList<Property> properties;
	private ArrayList<Tenant> tenants;
	private ArrayList<Lease> leases;
	private ArrayList<Lease> expiredLeases;

	/**
	 * 
	 * Constructs a new DatabaseUtility object with empty lists of properties,
	 * tenants, leases, and expired leases.
	 */
	private DatabaseUtility() {
		this.properties = new ArrayList<>();
		this.tenants = new ArrayList<>();
		this.leases = new ArrayList<>();
		this.expiredLeases = new ArrayList<>();
	}

	/**
	 * 
	 * Returns the single instance of the DatabaseUtility class.
	 * 
	 * @return The instance of the DatabaseUtility class.
	 */
	public static DatabaseUtility getInstance() {
		if (instance == null) {
			instance = new DatabaseUtility();
		}
		return instance;
	}

	/**
	 * 
	 * Returns the list of properties stored in the database.
	 * 
	 * @return The list of properties.
	 */
	public ArrayList<Property> getProperties() {
		return properties;
	}

	/**
	 * 
	 * Returns the list of tenants stored in the database.
	 * 
	 * @return The list of tenants.
	 */
	public ArrayList<Tenant> getTenants() {
		return tenants;
	}

	/**
	 * 
	 * Returns the list of leases stored in the database.
	 * 
	 * @return The list of leases.
	 */
	public ArrayList<Lease> getLeases() {
		return leases;
	}

	/**
	 * 
	 * Returns the list of expired leases stored in the database.
	 * 
	 * @return The list of expired leases.
	 */
	public ArrayList<Lease> getExpiredLeases() {
		return expiredLeases;
	}
}