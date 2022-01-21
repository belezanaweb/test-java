package br.com.blz.testjava.domain.entities;

public class Warehouse {
	String locality;
	int quantity;
	String type;
	
	public Warehouse(String locality, int quantity, String type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}
	
	public Warehouse() {
		super();
	}
	
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
}
