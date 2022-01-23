package br.com.blz.testjava.domain.entities;

import java.util.List;

public class Inventory {
	int quantity;
	List<Warehouse> warehouses;
	
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

	public List<Warehouse> getWharehouses() {
		return this.warehouses;
	}
	
	public void setWharehouses(List<Warehouse> wharehouses) {
		this.warehouses = wharehouses;
		this._calculateQuantity();
	}
	
	
	private void _calculateQuantity() {
		this.quantity = 0;
		for (Warehouse warehouse : this.warehouses) {
			this.quantity += warehouse.quantity;
		}
	}
	
	public void updateQuantity() {
		this._calculateQuantity();
	}
	
}
