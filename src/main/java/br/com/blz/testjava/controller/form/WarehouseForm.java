package br.com.blz.testjava.controller.form;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import br.com.blz.testjava.model.Warehouse;

public class WarehouseForm {

	@NotNull @NotEmpty
	private String locality;
	@NotNull @NotEmpty
	private long quantity;
	@NotNull @NotEmpty
	private String type;

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Warehouse converteWarehouse () {
	
		return new Warehouse(locality, quantity, type);
	}
	
}
