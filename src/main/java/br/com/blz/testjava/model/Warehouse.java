package br.com.blz.testjava.model;

public class Warehouse {
	
	private String locality;
	private Integer quantity;
	private TypeWarehouse type;
	
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public TypeWarehouse getType() {
		return type;
	}
	public void setType(TypeWarehouse type) {
		this.type = type;
	}
	

}
