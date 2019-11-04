package br.com.blz.testjava.entity;

import java.util.List;

public class Inventory {

	private List<Warehouse> warehouses;

	public Long getQuantity() {
		return this.warehouses.stream().mapToLong(Warehouse::getQuantity).sum();
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouse(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
