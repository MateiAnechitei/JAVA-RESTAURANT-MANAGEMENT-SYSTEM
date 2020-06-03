package business;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable{
	private int orderID;
	private String date;
	private int table;
	private boolean status;
	private float price;
	
	public Order(int table) {
		this.status = true;
		this.setPrice(0.0f);
		this.table = table;
	}
	
	public Order(int orderID, String date, int table) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.table = table;
		this.status = true;
		this.setPrice(0.0f);
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getOrderID() {
		return orderID;
	}
	
	public void setOrderID(int orderId) {
		this.orderID = orderId;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getTable() {
		return table;
	}
	
	public void setTable(int table) {
		this.table = table;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {	
		return this.orderID; // to be completed
	}
	
	@Override
	public boolean equals(Object o) {
		if( o == null) {
			return false;
		}
		if(!(o instanceof Order)) {
			return false;
		}	
		if(o == this) {
			return true;
		}
		return this.getOrderID() == ((Order) o).getOrderID();	
		
	}

}
