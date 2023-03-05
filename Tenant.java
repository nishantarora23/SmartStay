public class Tenant {

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
		return "Tenant ID : " + this.getTenantID() + "\nTenant Name: " + this.getFirstName() + " " + this.getLastName() + "\nTenant Email: "
				+ this.getEmail() + "\nTenant Phone Number: " + this.getPhoneNumber();
	}
}