package model;

public class Condo extends Property {
	
	private int unitNumber;
	private int streetNumber;
	
	public int getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public Condo(String streetName, String city, String postalCode, String province, String country,
			int noOfBedrooms, int noOfBathrooms, int squareFootage, int unitNumber, int streetNumber) {
		super(streetName, city, postalCode, province, country, noOfBedrooms, noOfBathrooms, squareFootage);
		this.unitNumber = unitNumber;
		this.streetNumber = streetNumber;
	}

	@Override
	public String getPropertyType() {
		return "Condo";
	}

	@Override
	public String toString() {
		return this.getPropertyType() + ", " + super.getPropertyId() + ", " + this.unitNumber + ", " + this.streetNumber + ", "
				 + super.getStreetName() + ", " + super.getCity() + ", " + super.getProvince() + ", " + super.getCountry()
				 + ", " + super.getPostalCode();
	}
	
	@Override
	public String getFullAddress() {
		return this.unitNumber + ", " + this.streetNumber + ", "
				 + super.getStreetName() + ", " + super.getCity() + ", " + super.getProvince() + ", " + super.getCountry()
				 + ", " + super.getPostalCode();
	}

}
