package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.RentalController;
import model.Apartment;
import model.Condo;
import model.House;
import model.Lease;
import model.Property;
import model.PropertyBuilder;
import model.Tenant;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//import java.util.Scanner;

/**
 * The RentalView class represents the view of the SmartStay (rental management
 * system). It handles user input and displays information related to
 * properties, tenants, and rental units. It interacts with the RentalController
 * to perform operations on the model.
 */
public class RentalView extends Application {

	private RentalController controller;
	private Stage stage;

	public RentalView() {
		// TODO Auto-generated constructor stub
	}

	public RentalView(RentalController controller) {
		this.controller = controller;
	}

	Image bannerImage = new Image("file:./resources/images/logo.jpg");
	ImageView bannerImageView = new ImageView(bannerImage);

	/**
	 * This method is an entry point for the JAVAFX application. It is the welcome
	 * screen which displays the menu in the form of action buttons.
	 */
	@Override
	public void start(Stage stage) {
		stage.setTitle("Welcome To SmartStay");
		this.stage = stage;

		bannerImageView.setFitWidth(400);
		bannerImageView.setPreserveRatio(true);
		Label label = new Label("What would you like to do?");
		label.setFont(Font.font("System", FontWeight.BOLD, 14));
		Button button1 = new Button("Add a property");
		button1.setPrefWidth(200);
		Button button2 = new Button("Add a tenant");
		button2.setPrefWidth(200);
		Button button3 = new Button("Rent a unit");
		button3.setPrefWidth(200);
		Button button4 = new Button("Display properties");
		button4.setPrefWidth(200);
		Button button5 = new Button("Display tenants");
		button5.setPrefWidth(200);
		Button button6 = new Button("Display rented units");
		button6.setPrefWidth(200);
		Button button7 = new Button("Display vacant units");
		button7.setPrefWidth(200);
		Button button8 = new Button("Display all leases");
		button8.setPrefWidth(200);
		Button button9 = new Button("Make Rent Payment");
		button9.setPrefWidth(200);
		Button button10 = new Button("Rent Payment Summary");
		button10.setPrefWidth(200);
		Button button11 = new Button("Notify potential tenants");
		button11.setPrefWidth(200);
		Button button0 = new Button("Exit");
		button0.setPrefWidth(200);

		button1.setOnAction(e -> addProperty());
		// button2.setOnAction(e -> addTenant());
		// button3.setOnAction(e -> rentUnit());
		// button4.setOnAction(e -> displayProperties());
		// button5.setOnAction(e -> displayTenants());
		// button6.setOnAction(e -> displayRentedUnits());
		// button7.setOnAction(e -> displayVacantUnits());
		// button8.setOnAction(e -> displayAllLeases());
		// button9.setOnAction(e -> payRent());
		// button10.setOnAction(e -> displayRentSummary());
		// button11.setOnAction(e -> notification());
		button0.setOnAction(e -> stage.close());

		VBox vbox = new VBox(10, bannerImageView, label, button1, button2, button3, button4, button5, button6, button7,
				button8, button9, button10, button11, button0);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #FFFFFF;");

		Scene scene = new Scene(vbox, 500, 700);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Prompts the user to select a property type and adds the property
	 * (Apartment/Condo/House) to the model.
	 */
	public void addProperty() {
		stage.setTitle("Add Property");
		bannerImageView.setFitWidth(400);
		bannerImageView.setPreserveRatio(true);

		Label label1 = new Label("Select property type:");
		label1.setFont(Font.font("System", FontWeight.BOLD, 14));
		Button button1 = new Button("Apartment");
		button1.setPrefWidth(100);
		Button button2 = new Button("Condo");
		button2.setPrefWidth(100);
		Button button3 = new Button("House");
		button3.setPrefWidth(100);
		Button button4 = new Button("Back to Main Menu");
		button4.setPrefWidth(200);

		button1.setOnAction(e -> addApartment());
		button2.setOnAction(e -> addCondo());
		button3.setOnAction(e -> addHouse());
		button4.setOnAction(e -> start(stage));

		HBox hbox = new HBox(10, button1, button2, button3);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));

		HBox hbox2 = new HBox(button4);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setPadding(new Insets(10));

		VBox vbox = new VBox(10, bannerImageView, label1, hbox, hbox2);

		Label titleLabel = new Label("Customer Review:");
		titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

		Label reviewLabel1 = new Label(
				"\"I stay at a property which was listed on SmartStay and I couldn't be happier with my experience. The booking agents were quick and responsive, the property was exactly as described by the agent. The property was clean, comfortable, and had all the amenities I needed to get started. I highly recommend SmartStay to anyone looking for a hassle-free property rental.\"");
		reviewLabel1.setFont(Font.font("System", FontPosture.ITALIC, 14));
		reviewLabel1.setWrapText(true);

		Label reviewLabel2 = new Label("-Sam Smith");
		reviewLabel2.setFont(Font.font("System", FontWeight.NORMAL, 14));

		VBox reviewBox = new VBox(titleLabel, reviewLabel1, reviewLabel2);
		reviewBox.setAlignment(Pos.CENTER);
		reviewBox.setSpacing(5);

		reviewBox.setStyle(
				"-fx-background-color: #F5F5F5; -fx-padding: 10px; -fx-border-color: #A9A9A9; -fx-border-width: 1px;");

		vbox.getChildren().add(reviewBox);

		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #FFFFFF;");

		Scene scene = new Scene(vbox, 500, 700);
		stage.setScene(scene);

	}

	/**
	 * Prompts the user to enter details about an apartment in the text fields and
	 * submit button to add apartment to the property.
	 */
	public void addApartment() {
		stage.setTitle("Add Apartment");
		bannerImageView.setFitWidth(400);
		bannerImageView.setPreserveRatio(true);
		Label label = new Label("\nAPARTMENT");
		label.setFont(Font.font("System", FontWeight.BOLD, 14));

		Label apartmentNumberLabel = new Label("Apartment Number:");
		TextField apartmentNumberField = new TextField();

		Label streetNameLabel = new Label("Street Name:");
		TextField streetNameField = new TextField();

		Label cityLabel = new Label("City:");
		TextField cityField = new TextField();

		Label provinceLabel = new Label("Province:");
		TextField provinceField = new TextField();

		Label countryLabel = new Label("Country:");
		TextField countryField = new TextField();

		Label postalCodeLabel = new Label("Postal Code:");
		TextField postalCodeField = new TextField();

		Label numBedroomsLabel = new Label("Number of Bedrooms:");
		TextField numBedroomsField = new TextField();

		Label numBathroomsLabel = new Label("Number of Bathrooms:");
		TextField numBathroomsField = new TextField();

		Label squareFootageLabel = new Label("Square Footage:");
		TextField squareFootageField = new TextField();

		Button submitButton = new Button("Submit");
		submitButton.setPrefWidth(150);
		submitButton.setOnAction(e -> {
			try {
				int apartmentNumber = Integer.parseInt(apartmentNumberField.getText());
				String streetName = streetNameField.getText();
				String city = cityField.getText();
				String province = provinceField.getText();
				String country = countryField.getText();
				String postalCode = postalCodeField.getText();
				int numBedrooms = Integer.parseInt(numBedroomsField.getText());
				int numBathrooms = Integer.parseInt(numBathroomsField.getText());
				int squareFootage = Integer.parseInt(squareFootageField.getText());

				if (apartmentNumberField.getText().isEmpty() || streetName.isEmpty() || city.isEmpty()
						|| province.isEmpty() || country.isEmpty() || postalCode.isEmpty()
						|| numBedroomsField.getText().isEmpty() || numBathroomsField.getText().isEmpty()
						|| squareFootageField.getText().isEmpty()) {
					throw new IllegalArgumentException("Please fill in all required fields.");
				}

				Apartment apartment = new PropertyBuilder().withStreetName(streetName).withCity(city)
						.withProvince(province).withCountry(country).withPostalCode(postalCode)
						.withApartmentNumber(apartmentNumber).withNumBedrooms(numBedrooms)
						.withNumBathrooms(numBathrooms).withSquareFootage(squareFootage).buildApartment();
				controller.addProperty(apartment);

				Label messageLabel = new Label("Apartment added successfully to the list of properties.");
				VBox vbox = new VBox(10);
				vbox.getChildren().addAll(messageLabel, new Separator());
				Scene scene = new Scene(vbox, 500, 650);
				stage.setScene(scene);

			} catch (NumberFormatException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all required fields with valid inputs.");
				alert.showAndWait();
			} catch (IllegalArgumentException ex) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		});
		Button button = new Button("Back to Property");
		button.setOnAction(e -> addProperty());
		button.setPrefWidth(150);
		HBox hbox = new HBox(10, submitButton, button);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		gridPane.addRow(1, apartmentNumberLabel, apartmentNumberField);
		gridPane.addRow(2, streetNameLabel, streetNameField);
		gridPane.addRow(3, cityLabel, cityField);
		gridPane.addRow(4, provinceLabel, provinceField);
		gridPane.addRow(5, countryLabel, countryField);
		gridPane.addRow(6, postalCodeLabel, postalCodeField);
		gridPane.addRow(7, numBedroomsLabel, numBedroomsField);
		gridPane.addRow(8, numBathroomsLabel, numBathroomsField);
		gridPane.addRow(9, squareFootageLabel, squareFootageField);
		gridPane.add(hbox, 0, 11, 2, 1);
		gridPane.setAlignment(Pos.CENTER);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(bannerImageView, label, gridPane);

		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #FFFFFF;");

		Scene scene = new Scene(vbox, 500, 700);
		stage.setScene(scene);
	}
	
	/**
	 * Prompts the user to enter details about a condo in the text fields and
	 * submit button to add condo to the property.
	 */
	public void addCondo() {
		stage.setTitle("Add Condo");
		bannerImageView.setFitWidth(400);
		bannerImageView.setPreserveRatio(true);
		Label label = new Label("\nCONDO");
		label.setFont(Font.font("System", FontWeight.BOLD, 14));

		Label unitNumberLabel = new Label("Unit Number:");
		TextField unitNumberField = new TextField();

		Label streetNameLabel = new Label("Street Name:");
		TextField streetNameField = new TextField();

		Label streetNumberLabel = new Label("Street Number:");
		TextField streetNumberField = new TextField();

		Label cityLabel = new Label("City:");
		TextField cityField = new TextField();

		Label provinceLabel = new Label("Province:");
		TextField provinceField = new TextField();

		Label countryLabel = new Label("Country:");
		TextField countryField = new TextField();

		Label postalCodeLabel = new Label("Postal Code:");
		TextField postalCodeField = new TextField();

		Label numBedroomsLabel = new Label("Number of Bedrooms:");
		TextField numBedroomsField = new TextField();

		Label numBathroomsLabel = new Label("Number of Bathrooms:");
		TextField numBathroomsField = new TextField();

		Label squareFootageLabel = new Label("Square Footage:");
		TextField squareFootageField = new TextField();

		Button submitButton = new Button("Submit");
		submitButton.setPrefWidth(150);
		submitButton.setOnAction(e -> {
			try {
				int unitNumber = Integer.parseInt(unitNumberField.getText());
				String streetName = streetNameField.getText();
				int streetNumber = Integer.parseInt(streetNumberField.getText());
				String city = cityField.getText();
				String province = provinceField.getText();
				String country = countryField.getText();
				String postalCode = postalCodeField.getText();
				int numBedrooms = Integer.parseInt(numBedroomsField.getText());
				int numBathrooms = Integer.parseInt(numBathroomsField.getText());
				int squareFootage = Integer.parseInt(squareFootageField.getText());

				if (unitNumberField.getText().isEmpty() || streetName.isEmpty() || city.isEmpty()
						|| streetNumberField.getText().isEmpty() || province.isEmpty() || country.isEmpty()
						|| postalCode.isEmpty() || numBedroomsField.getText().isEmpty()
						|| numBathroomsField.getText().isEmpty() || squareFootageField.getText().isEmpty()) {
					throw new IllegalArgumentException("Please fill in all required fields.");
				}

				Condo condo = new PropertyBuilder().withStreetName(streetName).withCity(city)
						.withStreetNumber(streetNumber).withProvince(province).withCountry(country)
						.withPostalCode(postalCode).withUnitNumber(unitNumber).withNumBedrooms(numBedrooms)
						.withNumBathrooms(numBathrooms).withSquareFootage(squareFootage).buildCondo();
				controller.addProperty(condo);

				Label messageLabel = new Label("Condo added successfully to the list of properties.");
				VBox vbox = new VBox(10);
				vbox.getChildren().addAll(messageLabel, new Separator());
				Scene scene = new Scene(vbox, 500, 650);
				stage.setScene(scene);

			} catch (NumberFormatException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all required fields with valid inputs.");
				alert.showAndWait();
			} catch (IllegalArgumentException ex) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		});
		Button button = new Button("Back to Property");
		button.setOnAction(e -> addProperty());
		button.setPrefWidth(150);
		HBox hbox = new HBox(10, submitButton, button);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		gridPane.addRow(1, unitNumberLabel, unitNumberField);
		gridPane.addRow(2, streetNumberLabel, streetNumberField);
		gridPane.addRow(3, streetNameLabel, streetNameField);
		gridPane.addRow(4, cityLabel, cityField);
		gridPane.addRow(5, provinceLabel, provinceField);
		gridPane.addRow(6, countryLabel, countryField);
		gridPane.addRow(7, postalCodeLabel, postalCodeField);
		gridPane.addRow(8, numBedroomsLabel, numBedroomsField);
		gridPane.addRow(9, numBathroomsLabel, numBathroomsField);
		gridPane.addRow(10, squareFootageLabel, squareFootageField);
		gridPane.add(hbox, 0, 12, 2, 1);
		gridPane.setAlignment(Pos.CENTER);
		VBox vbox = new VBox();
		vbox.getChildren().addAll(bannerImageView, label, gridPane);

		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #FFFFFF;");

		Scene scene = new Scene(vbox, 500, 700);
		stage.setScene(scene);
	}
	
	/**
	 * Prompts the user to enter details about a house in the text fields and
	 * submit button to add house to the property.
	 */
	public void addHouse() {
		stage.setTitle("Add House");
		bannerImageView.setFitWidth(400);
		bannerImageView.setPreserveRatio(true);

		Label label = new Label("\nHOUSE");
		label.setFont(Font.font("System", FontWeight.BOLD, 14));

		Label houseNumberLabel = new Label("House Number:");
		TextField houseNumberField = new TextField();

		Label streetNameLabel = new Label("Street Name:");
		TextField streetNameField = new TextField();

		Label streetNumberLabel = new Label("Street Number:");
		TextField streetNumberField = new TextField();

		Label cityLabel = new Label("City:");
		TextField cityField = new TextField();

		Label provinceLabel = new Label("Province:");
		TextField provinceField = new TextField();

		Label countryLabel = new Label("Country:");
		TextField countryField = new TextField();

		Label postalCodeLabel = new Label("Postal Code:");
		TextField postalCodeField = new TextField();

		Label numBedroomsLabel = new Label("Number of Bedrooms:");
		TextField numBedroomsField = new TextField();

		Label numBathroomsLabel = new Label("Number of Bathrooms:");
		TextField numBathroomsField = new TextField();

		Label squareFootageLabel = new Label("Square Footage:");
		TextField squareFootageField = new TextField();

		Button submitButton = new Button("Submit");
		submitButton.setPrefWidth(150);
		submitButton.setOnAction(e -> {
			try {
				int houseNumber = Integer.parseInt(houseNumberField.getText());
				String streetName = streetNameField.getText();
				int streetNumber = Integer.parseInt(streetNumberField.getText());
				String city = cityField.getText();
				String province = provinceField.getText();
				String country = countryField.getText();
				String postalCode = postalCodeField.getText();
				int numBedrooms = Integer.parseInt(numBedroomsField.getText());
				int numBathrooms = Integer.parseInt(numBathroomsField.getText());
				int squareFootage = Integer.parseInt(squareFootageField.getText());

				if (houseNumberField.getText().isEmpty() || streetName.isEmpty() || city.isEmpty()
						|| streetNumberField.getText().isEmpty() || province.isEmpty() || country.isEmpty()
						|| postalCode.isEmpty() || numBedroomsField.getText().isEmpty()
						|| numBathroomsField.getText().isEmpty() || squareFootageField.getText().isEmpty()) {
					throw new IllegalArgumentException("Please fill in all required fields.");
				}

				House house = new PropertyBuilder().withStreetName(streetName).withCity(city)
						.withStreetNumber(streetNumber).withProvince(province).withCountry(country)
						.withPostalCode(postalCode).withHouseNumber(houseNumber).withNumBedrooms(numBedrooms)
						.withNumBathrooms(numBathrooms).withSquareFootage(squareFootage).buildHouse();
				controller.addProperty(house);

				Label messageLabel = new Label("House added successfully to the list of properties.");
				VBox vbox = new VBox(10);
				vbox.getChildren().addAll(messageLabel, new Separator());
				Scene scene = new Scene(vbox, 500, 650);
				stage.setScene(scene);

			} catch (NumberFormatException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all required fields with valid inputs.");
				alert.showAndWait();
			} catch (IllegalArgumentException ex) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		});
		Button button = new Button("Back to Property");
		button.setOnAction(e -> addProperty());
		button.setPrefWidth(150);
		HBox hbox = new HBox(10, submitButton, button);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		gridPane.addRow(1, houseNumberLabel, houseNumberField);
		gridPane.addRow(2, streetNumberLabel, streetNumberField);
		gridPane.addRow(3, streetNameLabel, streetNameField);
		gridPane.addRow(4, cityLabel, cityField);
		gridPane.addRow(5, provinceLabel, provinceField);
		gridPane.addRow(6, countryLabel, countryField);
		gridPane.addRow(7, postalCodeLabel, postalCodeField);
		gridPane.addRow(8, numBedroomsLabel, numBedroomsField);
		gridPane.addRow(9, numBathroomsLabel, numBathroomsField);
		gridPane.addRow(10, squareFootageLabel, squareFootageField);
		gridPane.add(hbox, 0, 12, 2, 1);
		gridPane.setAlignment(Pos.CENTER);
		VBox vbox = new VBox();
		vbox.getChildren().addAll(bannerImageView, label, gridPane);

		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #FFFFFF;");

		Scene scene = new Scene(vbox, 500, 700);
		stage.setScene(scene);
	}
}

//	/**
//	 * Prompts the user to input details of a new Tenant and adds it to the
//	 * Controller's list of tenants.
//	 * 
//	 * @param scanner a Scanner object used to receive input from the user
//	 */
//	public void addTenant(Scanner scanner) {
//		System.out.println("\nTENANT DETAILS:");
//		System.out.print("Enter the first name: ");
//		String firstName = scanner.nextLine();
//
//		System.out.print("Enter the last name: ");
//		String lastName = scanner.nextLine();
//
//		System.out.print("Enter the phone number: ");
//		String phoneNumber = scanner.nextLine();
//
//		System.out.print("Enter the email: ");
//		String email = scanner.nextLine();
//
//		Tenant tenant = new Tenant(firstName, lastName, phoneNumber, email);
//		controller.addTenant(tenant);
//		System.out.println("Hi " + tenant.getFirstName() + ",\nWelcome to SmartStay! Your rental ID is: "
//				+ tenant.getTenantID() + ". You are successfully registered with us. "
//				+ "Please remember to keep this ID for any future communication with our rental system.\n");
//	}
//
//	/**
//	 * Prompts the user to select a Tenant and a Property, then adds a new Lease to
//	 * the Controller's list of leases.
//	 * 
//	 * @param scanner a Scanner object used to receive input from the user
//	 */
//	public void rentUnit(Scanner scanner) {
//
//		// drop down for tenant selection
//		System.out.print("Enter the tenant ID: ");
//		int tenantID = scanner.nextInt();
//		scanner.nextLine();
//
//		Tenant tenant = controller.getTenant(tenantID);
//		if (tenant == null) {
//			System.out.println("Invalid tenant ID.\n");
//			return;
//		}
//
//		ArrayList<Property> properties = controller.getAllProperties();
//		System.out.println("\nSelect a property to rent:");
//		for (int i = 0; i < properties.size(); i++) {
//			Property property = properties.get(i);
//			System.out.println("\t" + property);
//		}
//
//		System.out.print("\nEnter your choice: ");
//		int propertyChoice = scanner.nextInt();
//		scanner.nextLine();
//
//		if (propertyChoice < 1 || propertyChoice > properties.size()) {
//			System.out.println("Invalid property selection.\n");
//			return;
//		}
//
//		Property property = properties.get(propertyChoice - 1);
//
//		System.out.print("Enter the lease start date (yyyy-MM-dd): ");
//		String startDateStr = scanner.nextLine();
//		LocalDate startDate = LocalDate.parse(startDateStr);
//
//		System.out.print("Enter the lease end date (yyyy-MM-dd): ");
//		String endDateStr = scanner.nextLine();
//		LocalDate endDate = LocalDate.parse(endDateStr);
//
//		ArrayList<Property> vacantProperties = controller.getVacantUnits();
//
//		boolean isVacant = false;
//		for (Property vacantProperty : vacantProperties) {
//			if (vacantProperty.getPropertyId() == property.getPropertyId()) {
//				isVacant = true;
//				break;
//			}
//		}
//
//		Lease coincidingLease = controller.getLeaseByPropertyAndDates(property, startDate, endDate);
//
//		if (isVacant || coincidingLease != null) {
//
//			// Check if there is an existing lease during the specified dates
//			if (coincidingLease != null) {
//				// Ask user if they want to change the lease data or exit
//				System.out.println("\nThere is an existing lease for this property during the specified dates."
//						+ "\nDates of coinciding lease : " + coincidingLease.getStartDate() + " to "
//						+ coincidingLease.getEndDate());
//				System.out.print("\nDo you want to change the lease data? (Y/N): ");
//
//				String response = scanner.nextLine();
//				if (response.equalsIgnoreCase("Y")) {
//
//					System.out.print("Enter the new start date (yyyy-MM-dd): ");
//					startDateStr = scanner.nextLine();
//					startDate = LocalDate.parse(startDateStr);
//
//					System.out.print("Enter the new end date (yyyy-MM-dd): ");
//					endDateStr = scanner.nextLine();
//					endDate = LocalDate.parse(endDateStr);
//				} else {
//					property.addInterestedTenants(tenant);
//					System.out.println(
//							"\nThe selected unit is currently rented out. \nA notification will be sent once the unit is available.\n");
//					return;
//				}
//			}
//			// Allow user to enter new lease data
//			System.out.print("Enter the rent amount: ");
//			double rentAmount = scanner.nextDouble();
//			scanner.nextLine();
//			Lease lease = new Lease(tenant, property, startDate, endDate, rentAmount);
//			controller.addLease(lease);
//			System.out.println("The tenant " + tenant.getFirstName() + " " + tenant.getLastName()
//					+ " has been successfully rented the unit at " + property.getFullAddress() + ".\n");
//		} else {
//			property.addInterestedTenants(tenant);
//			System.out.println(
//					"The selected unit is currently rented out. \nA notification will be sent once the unit is available.\n");
//		}
//	}
//
//	/**
//	 * Displays all properties by retrieving the list of properties from the
//	 * controller and printing them to the console.
//	 */
//	public void displayProperties() {
//		ArrayList<Property> properties = controller.getAllProperties();
//		System.out.println("List of Smart Stay properties: ");
//		for (Property property : properties) {
//			System.out.println("\t" + property);
//		}
//		System.out.println();
//	}
//
//	/**
//	 * Displays all tenants by retrieving the list of tenants from the controller
//	 * and printing them to the console.
//	 */
//	public void displayTenants() {
//		ArrayList<Tenant> tenants = controller.getAllTenants();
//		System.out.println("List of registered tenants: ");
//		for (Tenant tenant : tenants) {
//			System.out.println("\t" + tenant);
//		}
//		System.out.println();
//	}
//
//	/**
//	 * Displays all rented units by retrieving the list of leases from the
//	 * controller and printing them to the console if the lease is still active.
//	 */
//	private void displayRentedUnits() {
//		ArrayList<Lease> leases = controller.getAllLeases();
//		if (leases.isEmpty()) {
//			System.out.println("No rented unit records to display");
//		} else {
//			System.out.println("List of rented units: ");
//			int i = 1;
//			for (Lease lease : leases) {
//				if (!lease.isExpired()) {
//					System.out.println("\t" + i + ". " + lease);
//					i++;
//				}
//			}
//		}
//	}
//
//	/**
//	 * Displays all vacant units by retrieving the list of properties from the
//	 * controller that are currently vacant and printing them to the console.
//	 */
//	public void displayVacantUnits() {
//		ArrayList<Property> properties = controller.getVacantUnits();
//		if (properties.isEmpty()) {
//			System.out.println("No vacant unit records to display.");
//		} else {
//			System.out.println("List of vacant properties: ");
//			for (Property property : properties) {
//				System.out.println("\t" + property);
//			}
//		}
//		System.out.println();
//	}
//
//	/**
//	 * Displays all leases by retrieving the list of leases from the controller and
//	 * printing them to the console.
//	 */
//	public void displayAllLeases() {
//		ArrayList<Lease> leases = controller.getAllLeases();
//		if (leases.isEmpty()) {
//			System.out.println("No active lease records to display.");
//		} else {
//			System.out.println("List of leases:");
//			for (Lease lease : leases) {
//				System.out.println("\t" + lease);
//			}
//		}
//	}
//
//	/**
//	 * Sends notifications to all potential tenants of a property through the
//	 * controller.
//	 */
//	public void notification() {
//		HashMap<String, ArrayList<String>> propertyAndInterestedTenants = controller.notifyInterestedTenants();
//		if (propertyAndInterestedTenants.isEmpty()) {
//			System.out.println("No potential tenant records found.\n");
//		} else {
//			for (Map.Entry<String, ArrayList<String>> entry : propertyAndInterestedTenants.entrySet()) {
//				String propertyAddress = entry.getKey();
//				ArrayList<String> interestedTenantNames = entry.getValue();
//				System.out.println("Property: " + propertyAddress);
//				int i = 1;
//				for (String name : interestedTenantNames) {
//					System.out.println("\t" + i + "." + name + " has been notified.");
//				}
//				System.out.println();
//			}
//		}
//	}
//
//	/**
//	 * Displays the summary of all the paid and unpaid rents.
//	 */
//	public void displayRentSummary() {
//		HashMap<String, ArrayList<String>> paidOrNotPaidAndTenants = controller.displayRentPaymentSummary();
//		if (paidOrNotPaidAndTenants.isEmpty()) {
//			System.out.println("No tenant records found associated with a lease.\n");
//		} else {
//			ArrayList<String> paidTenants = paidOrNotPaidAndTenants.get("PAID");
//			ArrayList<String> unpaidTenants = paidOrNotPaidAndTenants.get("UNPAID");
//			if (paidTenants == null)
//				System.out.println("No tenant records found for paid rent.");
//			else {
//				System.out.println("Tenants with paid rent: ");
//				for (String tenant : paidTenants) {
//					System.out.println("\t" + tenant);
//				}
//				System.out.println();
//			}
//			if (unpaidTenants == null)
//				System.out.println("No tenant records found for un-paid rent.");
//			else {
//				System.out.println("Tenants with unpaid rent: ");
//				for (String tenant : unpaidTenants) {
//					System.out.println("\t" + tenant);
//				}
//				System.out.println();
//			}
//		}
//	}
//
//	/**
//	 * Makes rent payment
//	 */
//	public void payRent(Scanner scanner) {
//		ArrayList<Lease> leases = controller.getAllLeases();
//		if (leases.isEmpty())
//			System.out.println("No lease records found for which payment can be made.");
//		else {
//			System.out.print("Enter the lease ID for which the payment has to be made: ");
//			int leaseID = scanner.nextInt();
//			scanner.nextLine();
//			System.out.println("Enter the amount: ");
//			double amount = scanner.nextDouble();
//			scanner.nextLine();
//			controller.makeRentPayment(leaseID, amount);
//		}
//	}