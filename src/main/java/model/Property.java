package model;

import java.util.ArrayList;

abstract class Address {

	private String streetName;
	private String city;
	private String postalCode;
	private static int propertyID = 1000;
	
	public Address(String streetName, String city, String postalCode) {
		this.streetName = streetName;
		this.city = city;
		this.postalCode = postalCode;
		Address.propertyID++;
	}

	public static int getPropertyID() {
		return propertyID;
	}

	public static void setPropertyID(int propertyID) {
		Address.propertyID = propertyID;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public abstract String getPropertyType();

}

public abstract class Property extends Address {

	private String civicAddress;
	private int numBedrooms;
	private int numBathrooms;
	private int squareFootage;
	private ArrayList<Tenant> interestedTenants;
	

	public Property(String civicAddress,String streetName, String city, String postalCode,int numBedrooms, int numBathrooms, int squareFootage) {
		super(streetName, city, postalCode);
		this.civicAddress = civicAddress;
		this.numBedrooms = numBedrooms;
		this.numBathrooms = numBathrooms;
		this.squareFootage = squareFootage;
		this.interestedTenants = new ArrayList<>();
	}
	
	public String getCivicAddress() {
		return civicAddress;
	}

	public void setCivicAddress(String civicAddress) {
		this.civicAddress = civicAddress;
	}

	public int getNumBedrooms() {
		return numBedrooms;
	}

	public void setNumBedrooms(int numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	public int getNumBathrooms() {
		return numBathrooms;
	}

	public void setNumBathrooms(int numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}
	
	public void addInterestedTenant(Tenant tenant) {
        interestedTenants.add(tenant);
    }

    public void removeInterestedTenant(Tenant tenant) {
        interestedTenants.remove(tenant);
    }
    
    public ArrayList<Tenant> getAllInterestedTenants() {
    	return interestedTenants;
    }
    
    public abstract String getFullAddress();

}

