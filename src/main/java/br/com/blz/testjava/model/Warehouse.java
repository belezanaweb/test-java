package br.com.blz.testjava.model;

import br.com.blz.testjava.enums.Types;

public class Warehouse {
 
	private String locality;
	private Integer quantity;
	private Types type;
	
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
	
	public Types getType() {
		return type;
	}
	
	public void setType(Types type) {
		this.type = type;
	}
	
}
