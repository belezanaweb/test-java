package br.com.blz.testjava.data.dto;


public class WarehouseDTO {
	private String locality;
	private int quantity;
	private String type;
	
	public WarehouseDTO(String locality, int quantity, String type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}
	
	public WarehouseDTO() {
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


	@Override
	public String toString() {
		return "Locality: " + this.locality + " - Quantity:" + this.quantity + " - Type:" + this.type;
	}
	
	
}
