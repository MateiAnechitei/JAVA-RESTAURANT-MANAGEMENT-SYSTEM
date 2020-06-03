package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

@SuppressWarnings("serial")
public class CompositeProduct implements MenuItem, Serializable {
	
	private String name;
	private ArrayList<MenuItem> productComposition;
	private float price;
	
	public CompositeProduct(String name, ArrayList<MenuItem> productComposition) {
		super();
		this.name = name;
		this.productComposition = productComposition;
		this.price = computePrice();
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<MenuItem> getProductComposition() {
		return productComposition;
	}


	public void setProductComposition(ArrayList<MenuItem> productComposition) {
		this.productComposition = productComposition;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}
	
	public float computePrice() {
		float price = 0.0f;
		for(MenuItem item : this.productComposition) {
			price += item.computePrice();
		}
		return price;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Vector<String> addInTable(){
		Vector<String> newRow = new Vector<String>();
		newRow.add(name);
		newRow.add(String.valueOf(price));
		for(MenuItem mi : this.productComposition) {
			newRow.add(mi.toString());
		}
		return newRow;
	}
}
