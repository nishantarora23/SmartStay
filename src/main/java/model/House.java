package model;

public class House extends Property {
	
	private int streetNumber;
	
	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public House(String civicAddress, String streetName, String city, String postalCode, int streetNumber, int numBedrooms, int numBathrooms, int squareFootage) {
		super(civicAddress, streetName, city, postalCode, numBedrooms, numBathrooms, squareFootage);	
		this.streetNumber = streetNumber;
	}
	
	@Override
	public String getPropertyType() {
		return "House";
	}
	
	@Override
	public String toString() {
		return this.getPropertyType() + " " + Address.getPropertyID() + " " + this.getCivicAddress() + " " + this.getStreetName() + " " +
				this.getCity() + " " + this.getPostalCode() + " " + this.getStreetNumber() + " " + this.getNumBedrooms() + " "
				+ this.getNumBathrooms() + " " + this.getSquareFootage();
	}
	
	@Override
	public String getFullAddress() {
		return this.getPropertyType() + " " + Address.getPropertyID() + " " + this.getCivicAddress() + " " +
				this.getStreetNumber() + ", " + this.getStreetName() + ", " + this.getCity( )+ ", " + this.getPostalCode();
	}

}
