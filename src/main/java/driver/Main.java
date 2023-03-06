package driver;

import controller.RentalController;
import view.RentalView;

public class Main {

	public static void main(String[] args) {
		
		RentalController rc= new RentalController();
		RentalView rv = new RentalView(rc);
		rv.displayMenu();
	}
}
