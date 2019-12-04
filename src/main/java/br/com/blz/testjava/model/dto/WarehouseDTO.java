package br.com.blz.testjava.model.dto;

import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;

public class WarehouseDTO {
	private String locality;
	private int quantity;
	private WarehouseType type;
	
	public WarehouseDTO(Warehouse warehouse) {
		this.locality = warehouse.getLocality();
		this.quantity = warehouse.getQuantity();
		this.type = warehouse.getType();
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
