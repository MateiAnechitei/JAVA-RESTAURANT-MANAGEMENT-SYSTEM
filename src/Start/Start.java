package Start;

import business.Restaurant;
import data.RestaurantSerializator;
import presentation.AGUIController;
import presentation.AdministratorGraphicalUserInterface;
import presentation.ChefGraphicalUserInterface;
import presentation.ChefObserver;
import presentation.WGUIController;
import presentation.WaiterGraphicalUserInterface;

public class Start {

	public static void main(String[] args) throws Exception {
		RestaurantSerializator rs = new RestaurantSerializator();
		Restaurant r = rs.fileToRestaurant(args[0]);
		AdministratorGraphicalUserInterface agui = new AdministratorGraphicalUserInterface(r);
		AGUIController aguic = new AGUIController(r, agui);
		WaiterGraphicalUserInterface wgui = new WaiterGraphicalUserInterface(r);
		ChefGraphicalUserInterface cgui = new ChefGraphicalUserInterface();
		WGUIController wguic = new WGUIController(r, wgui, cgui);
		ChefObserver co = new ChefObserver(cgui);
		
	}

}
