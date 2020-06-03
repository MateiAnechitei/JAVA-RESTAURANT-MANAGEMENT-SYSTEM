package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;

@SuppressWarnings("serial")
public class Restaurant implements IRestaurantProcessing, Serializable{
	
	private int numberOfTables;
	private DefaultListModel<Boolean> tables;
	private DefaultListModel<MenuItem> menuItemList;
	private HashMap<Order, ArrayList<MenuItem>> madeOrders;
	private int nextOrderID;
	private int billID;
	
	public Restaurant() {
		super();
		this.numberOfTables = 10;
		this.tables = new DefaultListModel<Boolean>();
		for(int i = 0; i < this.numberOfTables; i++) {
			this.tables.addElement(false);
		}
		this.menuItemList = new DefaultListModel<MenuItem>();
		this.madeOrders = new HashMap<Order, ArrayList<MenuItem>>();
		this.nextOrderID = 1;
		this.billID = 1;
		
	}
	public Restaurant(int numberOfTables, DefaultListModel<Boolean> tables, DefaultListModel<MenuItem> menuItemList,
			HashMap<Order, ArrayList<MenuItem>> madeOrders, int nextOrderID, int billID) {
		super();
		this.numberOfTables = numberOfTables;
		this.tables = tables;
		this.menuItemList = menuItemList;
		this.madeOrders = madeOrders;
		this.nextOrderID = nextOrderID;
		this.billID = billID;
	}
	
	public int getNumberOfTables() {
		return numberOfTables;
	}
	
	public void setNumberOfTables(int numberOfTables) {
		this.numberOfTables = numberOfTables;
	}
	
	public DefaultListModel<Boolean> getTables() {
		return tables;
	}
	
	public void setTables(DefaultListModel<Boolean> tables) {
		this.tables = tables;
	}
	
	public DefaultListModel<MenuItem> getMenuItemList() {
		return menuItemList;
	}
	
	public void setMenuItemList(DefaultListModel<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}
	
	public HashMap<Order, ArrayList<MenuItem>> getMadeOrders() {
		return madeOrders;
	}
	
	public void setMadeOrders(HashMap<Order, ArrayList<MenuItem>> madeOrders) {
		this.madeOrders = madeOrders;
	}
	
	public int getNextOrderID() {
		return nextOrderID;
	}
	
	public void setNextOrderID(int nextOrderID) {
		this.nextOrderID = nextOrderID;
	}
	
	public void createMenuItem(String name, ArrayList<MenuItem> productComposition) {
		CompositeProduct compositeProduct = new CompositeProduct(name, productComposition);
		menuItemList.addElement(compositeProduct);
	}
	
	public void createMenuItem(String name, float price) {
		BaseProduct baseProduct = new BaseProduct(name, price);
		menuItemList.addElement(baseProduct);
	}
	
	public void deleteMenuItem(MenuItem item) {
		menuItemList.removeElement(item);
	}
	
	public void editMenuItem(MenuItem item, MenuItem editedItem) {
		menuItemList.set(menuItemList.indexOf(item), editedItem);
	}
	
	public void createOrder(Order newOrder, ArrayList<MenuItem> orderedItems) {
		LocalDate currentDate = LocalDate.now();
		newOrder.setDate(currentDate.toString());
		newOrder.setOrderID(this.nextOrderID);
		newOrder.setStatus(true);
		this.nextOrderID++;
		madeOrders.put(newOrder, orderedItems);
	}
	
	public float computeOrderPrice(Order order) {
		float orderPrice = 0.0f;
		
		ArrayList<MenuItem> orderedItems = madeOrders.get(order);
		for(MenuItem mi : orderedItems) {
			orderPrice += mi.computePrice();
		}
		return orderPrice;
	}
	
	Float finalPrice;
	public void generateBill(int table) {
		try {
			
			String fileName = "Bill_" + billID + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(LocalDate.now() + "\n");
			finalPrice = 0.0f;
			madeOrders.forEach( (k,v) -> {
				if(k.getTable() == table && k.getStatus() == true) {
					for(MenuItem mi : v) {
						try {
							writer.write(mi.toString() + "     " + mi.computePrice() + "\n");
							finalPrice += mi.computePrice();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					k.setStatus(false);
				}
			});
		writer.write("Total price: " + finalPrice + "\n");
		writer.close();
		tables.setElementAt(false, table - 1);
		} catch (Exception e) {
			System.out.println("Bill couldn't be generated!");
		}
		billID++;
	}
	
}
