package br.com.blz.testjava.model;

import br.com.blz.testjava.enums.Type;

public class Warehouse {

	private String locality;
	private Long quantity;
	private Type type;

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
