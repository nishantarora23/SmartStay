import java.time.LocalDate;

abstract class Address {

	private String streetName;
	private String city;
	private String postalCode;
	private static int propertyID=1000;

	public Address(String streetName, String city, String postalCode) {
		this.streetName = streetName;
		this.city = city;
		this.postalCode = postalCode;
		this.propertyID++;
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


abstract class Property extends Address{

	private String civicAddress;
	private int numBedrooms;
	private int numBathrooms;
	private int squareFootage;

	
	public Property(String civicAddress,String streetName, String city, String postalCode,int numBedrooms, int numBathrooms, int squareFootage) {
		super(streetName, city, postalCode);
		this.civicAddress = civicAddress;
		this.numBedrooms = numBedrooms;
		this.numBathrooms = numBathrooms;
		this.squareFootage = squareFootage;

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

}


class Apartment extends Property {
	private int apartmentNumber;
	
	public Apartment(String civicAddress, String streetName, String city, String postalCode, int apartmentNumber, int numBedrooms, int numBathrooms, int squareFootage) {
		super(civicAddress, streetName, city, postalCode,numBedrooms,numBathrooms,squareFootage);
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
		// TODO Auto-generated method stub
		return this.getPropertyType()+ " "+this.getPropertyID()+" "+this.getCivicAddress()+" "+this.getStreetName()+" "+
				this.getCity()+" "+this.getPostalCode()+" "+this.getApartmentNumber()+" "+this.getNumBedrooms()+" "
				+this.getNumBathrooms()+" "+this.getSquareFootage();

	}
}

class Condo extends Property {
	
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
		super(civicAddress, streetName, city, postalCode,numBedrooms,numBathrooms,squareFootage);	
		this.unitNumber = unitNumber;
		this.streetNumber=streetNumber;
	}

	@Override
	public String getPropertyType() {
		return "Condo";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getPropertyType()+ " "+this.getPropertyID()+" "+this.getCivicAddress()+" "+this.getStreetName()+" "+
				this.getCity()+" "+this.getPostalCode()+" "+this.getUnitNumber()+" "+this.getNumBedrooms()+" "
				+this.getNumBathrooms()+" "+this.getSquareFootage();

	}

}

class House extends Property {
	
	private int streetNumber;
	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public House(String civicAddress, String streetName, String city, String postalCode, int streetNumber, int numBedrooms, int numBathrooms, int squareFootage) {
		super(civicAddress, streetName, city, postalCode,numBedrooms,numBathrooms,squareFootage);	
		this.streetNumber = streetNumber;
	}

	@Override
	public String getPropertyType() {
		return "House";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getPropertyType()+ " "+this.getPropertyID()+" "+this.getCivicAddress()+" "+this.getStreetName()+" "+
				this.getCity()+" "+this.getPostalCode()+" "+this.getStreetNumber()+" "+this.getNumBedrooms()+" "
				+this.getNumBathrooms()+" "+this.getSquareFootage();

	}

}

class Tenant {
	private static int nextTenantID = 1; // static variable to generate unique tenant IDs
	private int tenantID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;

	public Tenant(String firstName, String lastName, String email, String phoneNumber) {
		this.tenantID = nextTenantID++; // generate a unique tenant ID
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getTenantID() {
		return tenantID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Tenant ID : "+this.getTenantID()+" "+this.getFirstName()+" "+this.getLastName()+" "+this.getEmail()+" "+this.getPhoneNumber()+" ";
	}
}


class Lease {
	private Tenant tenant;
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}

	private Address property;
	public Address getProperty() {
		return property;
	}

	public void setProperty(Address property) {
		this.property = property;
	}

	private LocalDate startDate;
	private LocalDate endDate;
	private double rentAmount;

	public Lease(Tenant tenant, Address property, LocalDate startDate, LocalDate endDate, double rentAmount) {
		this.tenant = tenant;
		this.property = property;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentAmount = rentAmount;
	}

	public boolean isExpired() {
		LocalDate currentDate = LocalDate.now();
		return currentDate.isAfter(endDate);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\nTenant :"+this.getTenant()+"\n"
		+ "Property :"+this.getProperty()+"\n"
		+ "Start Date : "+this.getStartDate()+"\n"
		+ "End Date : "+this.getEndDate()+"\n"
		+ "Rent Amount : "+this.getRentAmount();
	}
}
