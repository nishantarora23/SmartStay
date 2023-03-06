package model;

public class Apartment extends Property {
	private int apartmentNumber;
	
	public Apartment(String civicAddress, String streetName, String city, String postalCode, int apartmentNumber, int numBedrooms, int numBathrooms, int squareFootage) {
		super(civicAddress, streetName, city, postalCode, numBedrooms, numBathrooms, squareFootage);
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
		return this.getPropertyType() + " " + this.getApartmentNumber() + ", " + Address.getPropertyID() + " " + this.getCivicAddress() + " " 
				+ this.getStreetName() + ", " + this.getCity( )+ ", " + this.getPostalCode() + " " + this.getNumBedrooms() + " "
				+ this.getNumBathrooms() + " " + this.getSquareFootage();
	}
	
	@Override
	public String getFullAddress() {
		return this.getPropertyType() + " " + this.getApartmentNumber() + ", " + Address.getPropertyID() + " " + this.getCivicAddress() + " " 
				+ this.getStreetName() + ", " + this.getCity( )+ ", " + this.getPostalCode();
	}
}
