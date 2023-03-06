package model;

public class Condo extends Property {
	
	private String unitNumber;
	private int streetNumber;
	
	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public Condo(String civicAddress, String streetName, String city, String postalCode, int streetNumber, String unitNumber, int numBedrooms, int numBathrooms, int squareFootage) {
		super(civicAddress, streetName, city, postalCode, numBedrooms, numBathrooms, squareFootage);	
		this.unitNumber = unitNumber;
		this.streetNumber=streetNumber;
	}

	@Override
	public String getPropertyType() {
		return "Condo";
	}
	@Override
	public String toString() {
		return this.getPropertyType() + " " + Address.getPropertyID() + " " + this.getCivicAddress() + " " + this.getStreetName() + " " +
				this.getCity() + " " + this.getPostalCode() + " " + this.getUnitNumber() + " " + this.getNumBedrooms() + " "
				+ this.getNumBathrooms() + " " + this.getSquareFootage();
	}
	
	@Override
	public String getFullAddress() {
		return this.getPropertyType() + " " + this.getUnitNumber() + ", " + Address.getPropertyID() + " " + this.getCivicAddress() + " " +
				this.getStreetNumber() + ", " + this.getStreetName() + ", " + this.getCity( )+ ", " + this.getPostalCode();
	}

}
