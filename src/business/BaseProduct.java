package business;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class BaseProduct implements MenuItem, Serializable {
	
	private String name;
	private float price;
		
	public BaseProduct(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public float computePrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Vector<String> addInTable(){
		Vector<String> newRow = new Vector<String>();
		newRow.add(name);
		newRow.add(String.valueOf(price));
		return newRow;
	}
}
