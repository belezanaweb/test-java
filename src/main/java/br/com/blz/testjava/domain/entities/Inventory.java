package br.com.blz.testjava.domain.entities;

import java.util.List;

public class Inventory {
	private int quantity;
	private List<Warehouse> warehouses;
	
	public Inventory() {
		super();
	}
	
	public Inventory(int quantity, List<Warehouse> warehouses) {
		super();
		
		this.warehouses = warehouses;
		this._calculateQuantity();
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}
	
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
		this._calculateQuantity();
	}
	
	
	private void _calculateQuantity() {
		this.quantity = 0;
		for (Warehouse warehouse : this.warehouses) {
			this.quantity += warehouse.getQuantity();
		}
	}
	
	public void updateQuantity() {
		this._calculateQuantity();
	}
	
}
