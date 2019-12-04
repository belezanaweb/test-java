package br.com.blz.testjava.model.form;

import javax.validation.constraints.NotNull;

import br.com.blz.testjava.model.WarehouseType;

public class WarehouseForm {
	@NotNull
	private String locality;
	@NotNull
	private int quantity;
	@NotNull
	private WarehouseType type;
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setType(WarehouseType type) {
		this.type = type;
	}
	public String getLocality() {
		return locality;
	}
	public int getQuantity() {
		return quantity;
	}
	public WarehouseType getType() {
		return type;
	}
}
