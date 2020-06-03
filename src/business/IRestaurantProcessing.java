package business;

import java.util.ArrayList;

public interface IRestaurantProcessing {
	public void createMenuItem(String name, ArrayList<MenuItem> productComposition);
	public void createMenuItem(String name, float price);
	public void deleteMenuItem(MenuItem item);
	public void editMenuItem(MenuItem item, MenuItem editedItem);
	public void createOrder(Order newOrder, ArrayList<MenuItem> orderedItems);
	public float computeOrderPrice(Order order);
	public void generateBill(int table);
	
}
