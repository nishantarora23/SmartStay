package model;

public class House extends Property {

	private int streetNumber;
	private int houseNumber;

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public House(String streetName, String city, String postalCode, String province, String country, int noOfBedrooms,
			int noOfBathrooms, int squareFootage, int houseNumber, int streetNumber) {
		super(streetName, city, postalCode, province, country, noOfBedrooms, noOfBathrooms, squareFootage);
		this.houseNumber = houseNumber;
		this.streetNumber = streetNumber;
	}

	@Override
	public String getPropertyType() {
		return "House";
	}

	@Override
	public String toString() {
		return this.getPropertyType() + ", " + super.getPropertyId() + ", " + this.houseNumber + ", "
				+ this.streetNumber + ", " + super.getStreetName() + ", " + super.getCity() + ", " + super.getProvince()
				+ ", " + super.getCountry() + ", " + super.getPostalCode();
	}

	@Override
	public String getFullAddress() {
		return this.houseNumber + ", " + this.streetNumber + ", " + super.getStreetName() + ", " + super.getCity()
				+ ", " + super.getProvince() + ", " + super.getCountry() + ", " + super.getPostalCode();
	}

}
