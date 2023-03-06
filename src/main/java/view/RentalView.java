package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import controller.RentalController;
import model.Lease;
import model.Property;
import model.Tenant;

/**
 * The RentalView class represents the view of the SmartStay (rental management system). 
 * It handles user input and displays information related to properties, 
 * tenants, and rental units. It interacts with the RentalController to perform
 * operations on the model.
 */
public class RentalView {

	private RentalController controller;

	/**
	 * Creates a new RentalView with the given controller.
	 *
	 * @param controller the controller for the view to interact with
	 */
	public RentalView(RentalController controller) {
		this.controller = controller;
	}

	/**
	 * Displays the menu options for the rental management system.
	 */
	public void displayMenu() {
		System.out.println("1. Add a property");
		System.out.println("2. Add a tenant");
		System.out.println("3. Rent a unit");
		System.out.println("4. Display properties");
		System.out.println("5. Display tenants");
		System.out.println("6. Display rented units");
		System.out.println("7. Display vacant units");
		System.out.println("8. Rent Payment Summary");
		System.out.println("9. Notification");
		
		System.out.println("0. Exit");
	}

	/**
	 * Runs the rental management system. Handles user input and performs operations based on the input.
	 */
	public void run() {
		Scanner scanner = new Scanner(System.in);
		int choice = -1;

		while (choice != 0) {
			displayMenu();
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addProperty(scanner);
				break;
			case 2:
				addTenant(scanner);
				break;
			case 3:
				rentUnit(scanner);
				break;
			case 4:
				displayProperties();
				break;
			case 5:
				displayTenants();
				break;
			case 6:
				displayRentedUnits();
				break;
			case 7:
				displayVacantUnits();
				break;
			case 0:
				System.out.println();
				break;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	/**
	 * Prompts the user to select a property type and adds the property to the model.
	 *
	 * @param scanner the scanner to read user input
	 */
	private void addProperty(Scanner scanner) {
		System.out.println("Select property type: ");
		System.out.println("1. Apartment");
		System.out.println("2. Condo");
		System.out.println("3. House");
		System.out.print("Enter your choice: ");
		int propertyType = scanner.nextInt();
		scanner.nextLine();

		switch (propertyType) {
		case 1:
			addApartment(scanner);
			break;
		case 2:
			addCondo(scanner);
			break;
		case 3:
			addHouse(scanner);
			break;
		default:
			System.out.println("Invalid property type.");
		}
	}

	/**
	 * Prompts the user to enter details about an apartment and adds it to the model.
	 *
	 * @param scanner the scanner to read user input
	 */
	private void addApartment(Scanner scanner) {
		System.out.println("Enter the civic address:");
		String civicAddress = scanner.nextLine();
		System.out.println("Enter the street name:");
		String streetName = scanner.nextLine();
		System.out.println("Enter the city:");
		String city = scanner.nextLine();
		System.out.println("Enter the postal code:");
		String postalCode = scanner.nextLine();
		System.out.println("Enter the apartment number:");
		int apartmentNumber = scanner.nextInt();
		System.out.println("Enter the number of bedrooms:");
		int numBedrooms = scanner.nextInt();
		System.out.println("Enter the number of bathrooms:");
		int numBathrooms = scanner.nextInt();
		System.out.println("Enter the square footage:");
		int squareFootage = scanner.nextInt();
		Apartment apartment = new Apartment(civicAddress, streetName, city, postalCode, apartmentNumber, numBedrooms, numBathrooms, squareFootage);
		System.out.println("Apartment added successfully.");
		controller.addProperty(apartment);
	}

	/**
	 * Prompts the user to input details of a new Condo and adds it to the Controller's list of properties.
	 * 
	 * @param scanner a Scanner object used to receive input from the user
	 */
	private void addCondo(Scanner scanner) {

		System.out.println("Enter the civic address:");
		String civicAddress = scanner.nextLine();

		System.out.println("Enter the street name:");
		String streetName = scanner.nextLine();

		System.out.println("Enter the city:");
		String city = scanner.nextLine();

		System.out.println("Enter the postal code:");
		String postalCode = scanner.nextLine();

		System.out.println("Enter the street number:");
		int streetNumber = scanner.nextInt();
		scanner.nextLine(); // consume the new line character

		System.out.println("Enter the unit number:");
		String unitNumber = scanner.nextLine();

		System.out.println("Enter the number of bedrooms:");
		int numBedrooms = scanner.nextInt();

		System.out.println("Enter the number of bathrooms:");
		int numBathrooms = scanner.nextInt();

		System.out.println("Enter the square footage:");
		int squareFootage = scanner.nextInt();

		Condo condo = new Condo(civicAddress, streetName, city, postalCode, streetNumber, unitNumber, numBedrooms, numBathrooms, squareFootage);
		controller.addProperty(condo);
	}

	/**
	 * Prompts the user to input details of a new House and adds it to the Controller's list of properties.
	 * 
	 * @param scanner a Scanner object used to receive input from the user
	 */
	private void addHouse(Scanner scanner) {

		System.out.println("Enter the civic address: ");
		String civicAddress = scanner.nextLine();

		System.out.println("Enter the street name: ");
		String streetName = scanner.nextLine();

		System.out.println("Enter the city: ");
		String city = scanner.nextLine();

		System.out.println("Enter the postal code: ");
		String postalCode = scanner.nextLine();

		System.out.println("Enter the street number: ");
		int streetNumber = scanner.nextInt();

		System.out.println("Enter the number of bedrooms: ");
		int numBedrooms = scanner.nextInt();

		System.out.println("Enter the number of bathrooms: ");
		int numBathrooms = scanner.nextInt();

		System.out.println("Enter the square footage: ");
		int squareFootage = scanner.nextInt();

		House house = new House(civicAddress, streetName, city, postalCode, streetNumber, numBedrooms, numBathrooms, squareFootage);
		controller.addProperty(house);
	}

	/**
	 * Prompts the user to input details of a new Tenant and adds it to the Controller's list of tenants.
	 * 
	 * @param scanner a Scanner object used to receive input from the user
	 */
	private void addTenant(Scanner scanner) {
		System.out.print("Enter the first name: ");
		String firstName = scanner.nextLine();

		System.out.print("Enter the last name: ");
		String lastName = scanner.nextLine();

		System.out.print("Enter the phone number: ");
		String phoneNumber = scanner.nextLine();

		System.out.print("Enter the email: ");
		String email = scanner.nextLine();

		Tenant tenant = new Tenant(firstName, lastName, phoneNumber, email);
		System.out.println("Hi " + tenant.getFirstName() + ",\nWelcome to SmartStay! Your rental ID is: " + tenant.getTenantID() + 
				". Please remember to keep this ID for any future communication with our rental system.");
		controller.addTenant(tenant);
	}

	/**
	 * Prompts the user to select a Tenant and a Property, then adds a new Lease to the Controller's list of leases.
	 * 
	 * @param scanner a Scanner object used to receive input from the user
	 */
	private void rentUnit(Scanner scanner) {

		//drop down for tenant selection
		System.out.print("Enter the tenant ID: ");
		int tenantID = scanner.nextInt();
		scanner.nextLine();

		Tenant tenant = controller.getTenant(tenantID);
		if (tenant == null) {
			System.out.println("Invalid tenant ID.");
			return;
		}

		ArrayList<Property> properties = controller.getAllProperties();
		//		if (properties.isEmpty()) {
		//			System.out.println("Sorry, there are no vacant units.");
		//			return;
		//		}

		System.out.println("Select a property to rent:");
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			System.out.println((i+1) + ". " + property.getCivicAddress());
		}

		System.out.print("Enter your choice: ");
		int propertyChoice = scanner.nextInt();
		scanner.nextLine();

		if (propertyChoice < 1 || propertyChoice > properties.size()) {
			System.out.println("Invalid choice.");
			return;
		}

		Property property = properties.get(propertyChoice - 1);

		ArrayList<Property> vacantProperties = controller.getVacantUnits();



		System.out.print("Enter the lease start date (yyyy-MM-dd): ");
		String startDateStr = scanner.nextLine();
		LocalDate startDate = LocalDate.parse(startDateStr);

		System.out.print("Enter the lease end date (yyyy-MM-dd): ");
		String endDateStr = scanner.nextLine();
		LocalDate endDate = LocalDate.parse(endDateStr);

		System.out.print("Enter the rent amount: ");
		double rentAmount = scanner.nextDouble();
		scanner.nextLine();

		Lease lease = new Lease(tenant, property, startDate, endDate, rentAmount);
		controller.addLease(lease);
	}

	/**
	 * Displays all properties by retrieving the list of properties
	 * from the controller and printing them to the console.
	 */
	private void displayProperties() {
		ArrayList<Property> properties = controller.getAllProperties();
		for (Property property : properties) {
			System.out.println(property);
		}
	}

	/**
	 * Displays all tenants by retrieving the list of tenants from the 
	 * controller and printing them to the console.
	 */
	private void displayTenants() {
		ArrayList<Tenant> tenants = controller.getAllTenants();
		for (Tenant tenant : tenants) {
			System.out.println(tenant);
		}
	}

	/**
	 * 	Displays all rented units by retrieving the list of leases from the
	 * controller and printing them to the console if the lease is still active.
	 */
	private void displayRentedUnits() {
		ArrayList<Lease> leases = controller.getAllLeases();
		for (Lease lease : leases) {
			if (lease.getEndDate().isAfter(LocalDate.now())) {
				System.out.println(lease);
			}
		}
	}

	/**
	 * 	Displays all vacant units by retrieving the list of properties
	 * from the controller that are currently vacant and printing them
	 * to the console.
	 */
	private void displayVacantUnits() {
		ArrayList<Property> properties = controller.getVacantUnits();
		for (Property property : properties) {
			System.out.println(property);
		}
	}
}