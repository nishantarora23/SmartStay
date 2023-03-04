class RentalView {


	private RentalController controller;

	public RentalView(RentalController controller) {
		this.controller = controller;
	}

	public void displayMenu() {
		System.out.println("1. Add a property");
		System.out.println("2. Add a tenant");
		System.out.println("3. Rent a unit");
		System.out.println("4. Display properties");
		System.out.println("5. Display tenants");
		System.out.println("6. Display rented units");
		System.out.println("7. Display vacant units");
		System.out.println("0. Exit");
	}
	
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

}