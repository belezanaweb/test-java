package br.com.blz.testjava.dto;

import java.util.List;

public class Inventory {
	private int quantity;
	private List<Warehouses> warehouses;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity() {
		quantity = warehouses.stream().mapToInt(o -> o.getQuantity()).sum();
	}

	public List<Warehouses> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouses> warehouses) {
		this.warehouses = warehouses;
	}

}
