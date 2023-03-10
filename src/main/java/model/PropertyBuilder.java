package model;

public class PropertyBuilder {
	private String streetName;
	private String city;
	private String postalCode;
	private String province;
	private String country;
	private int noOfBedrooms;
	private int noOfBathrooms;
	private int squareFootage;
	private int apartmentNumber;
	private int unitNumber;
	private int houseNumber;
	private int streetNumber;

	public PropertyBuilder withStreetName(String streetName) {
		this.streetName = streetName;
		return this;
	}

	public PropertyBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	public PropertyBuilder withPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	public PropertyBuilder withProvince(String province) {
		this.province = province;
		return this;
	}

	public PropertyBuilder withCountry(String country) {
		this.country = country;
		return this;
	}

	public PropertyBuilder withNumBedrooms(int numBedrooms) {
		this.noOfBedrooms = numBedrooms;
		return this;
	}

	public PropertyBuilder withNumBathrooms(int numBathrooms) {
		this.noOfBathrooms = numBathrooms;
		return this;
	}

	public PropertyBuilder withSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
		return this;
	}

	public PropertyBuilder withApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
		return this;
	}

	public PropertyBuilder withUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
		return this;
	}

	public PropertyBuilder withHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
		return this;
	}

	public PropertyBuilder withStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
		return this;
	}

	public Apartment buildApartment() {
		return new Apartment(streetName, city, postalCode, province, country,
				noOfBedrooms, noOfBathrooms, squareFootage, apartmentNumber);
	}

	public Condo buildCondo() {
		return new Condo(streetName, city, postalCode, province, country,
				noOfBedrooms, noOfBathrooms, squareFootage, unitNumber, streetNumber);
	}

	public House buildHouse() {
		return new House(streetName, city, postalCode, province, country,
				noOfBedrooms, noOfBathrooms, squareFootage, houseNumber, streetNumber);
	}
}

