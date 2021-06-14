package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {
	private Integer quantity;
	private List<Warehouse> warehouses;

	public Inventory() {
	}

	public Inventory(List<Warehouse> warehouses) {
		super();
		this.warehouses = warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@Override
	public String toString() {
		return "Inventory [quantity=" + this.quantity + ", warehouses=" + this.warehouses + "]";
	}

}
