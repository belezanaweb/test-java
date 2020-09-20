package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {

	private List<Warehouse> warehouses;
	private Integer quantity;

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
