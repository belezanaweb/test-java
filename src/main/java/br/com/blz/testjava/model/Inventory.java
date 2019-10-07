package br.com.blz.testjava.model;

import java.util.Collection;

public class Inventory {

	private Long quantity;
	private Collection<Warehouse> warehouses;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Collection<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Collection<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
