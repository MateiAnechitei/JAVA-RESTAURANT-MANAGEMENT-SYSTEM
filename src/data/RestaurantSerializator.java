package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import business.Restaurant;

public class RestaurantSerializator {
	public void restaurantToFile(Restaurant r) throws Exception {
		FileOutputStream fout = new FileOutputStream("restaurant.ser");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		
		out.writeObject(r);
		out.flush();
		out.close();
	}
	
	public Restaurant fileToRestaurant(String s) throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(s));
		Restaurant r =(Restaurant)in.readObject();
		
		in.close();
		return r;
	}
}
