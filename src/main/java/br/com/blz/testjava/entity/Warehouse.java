package br.com.blz.testjava.entity;

import br.com.blz.testjava.enums.WarehouseType;

public class Warehouse {

	private String locality;
	private Integer quantity;
	private WarehouseType type;

	public Warehouse() {}
	
	public Warehouse(String locality, Integer quantity, WarehouseType type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public static Warehouse valueOf(String locality, Integer quantity, WarehouseType type) {
		return new Warehouse(locality, quantity, type);
	}
	
	public String getLocality() {
		return locality;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public WarehouseType getType() {
		return type;
	}
}
