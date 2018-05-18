package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {
	private List<Warehouse> warehouses;
	private int quantity;

	protected Inventory() {
	}

	public int getQuantity() {
		return quantity;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void calculeQuantity() {
		if(warehouses != null){
			final int quantity = warehouses.stream().mapToInt(Warehouse::getQuantity).sum();
			this.quantity = quantity;
		}
	}
}
