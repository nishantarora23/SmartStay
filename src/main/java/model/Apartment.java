package model;

public class Apartment extends Property {
	private int apartmentNumber;

	public Apartment(String streetName, String city, String postalCode, String province, String country,
			int noOfBedrooms, int noOfBathrooms, int squareFootage, int apartmentNumber) {
		super(streetName, city, postalCode, province, country, noOfBedrooms, noOfBathrooms, squareFootage);
		this.apartmentNumber = apartmentNumber;
	}

	public int getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	@Override
	public String getPropertyType() {
		return "Apartment";
	}

	@Override
	public String toString() {
		return this.getPropertyType() + ", " + super.getPropertyId() + ", " + this.apartmentNumber + ", "
				 + super.getStreetName() + ", " + super.getCity() + ", " + super.getProvince() + ", " + super.getCountry()
				 + ", " + super.getPostalCode();
	}
	
	@Override
	public String getFullAddress() {
		return this.apartmentNumber + ", "
				 + super.getStreetName() + ", " + super.getCity() + ", " + super.getProvince() + ", " + super.getCountry()
				 + ", " + super.getPostalCode();
	}
}
