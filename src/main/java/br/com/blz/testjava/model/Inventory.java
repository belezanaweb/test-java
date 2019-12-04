package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {
	private List<Warehouse> warehouses;
	public Inventory(List<Warehouse> ws) {
		this.warehouses = ws;
	}
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	public void setWharehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	public int getQuantity() {
		return this.warehouses.stream().mapToInt(Warehouse::getQuantity).sum();
	}	
}
