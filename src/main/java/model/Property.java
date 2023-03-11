package model;

import java.util.ArrayList;

public abstract class Property {
	private static int nextPropertyId = 1;
	private int propertyId;
	private String streetName;
	private String city;
	private String postalCode;
	private String province;
	private String country;
	private int noOfBedrooms;
	private int noOfBathrooms;
	private double squareFootage;

	ArrayList<Tenant> interestedTenants = new ArrayList<>();

	public Property(String streetName, String city, String postalCode, String province, String country,
			int noOfBedrooms, int noOfBathrooms, double squareFootage) {
		this.propertyId=Property.nextPropertyId++;
		this.streetName = streetName;
		this.city = city;
		this.postalCode = postalCode;
		this.province = province;
		this.country = country;
		this.noOfBedrooms = noOfBedrooms;
		this.noOfBathrooms = noOfBathrooms;
		this.squareFootage = squareFootage;
	}

	public int getPropertyId() {
		return propertyId;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNoOfBedrooms() {
		return noOfBedrooms;
	}

	public void setNoOfBedrooms(int noOfBedrooms) {
		this.noOfBedrooms = noOfBedrooms;
	}

	public int getNoOfBathrooms() {
		return noOfBathrooms;
	}

	public void setNoOfBathrooms(int noOfBathrooms) {
		this.noOfBathrooms = noOfBathrooms;
	}

	public double getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(double squareFootage) {
		this.squareFootage = squareFootage;
	}

	public abstract String getFullAddress();

	public abstract String getPropertyType();

	public ArrayList<Tenant> getAllInterestedTenants() {
		return interestedTenants;
	}
	
	public void addInterestedTenants(Tenant tenant) {
		interestedTenants.add(tenant);
	}
	
	public void removeInterestedTenants(Tenant tenant) {
		interestedTenants.add(tenant);
	}

}
