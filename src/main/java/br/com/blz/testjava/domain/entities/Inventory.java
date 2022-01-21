package br.com.blz.testjava.domain.entities;

import java.util.List;

public class Inventory {
	int quantity;
	List<Warehouse> wharehouses;
	
	public int getQuantity() {
		return quantity;
	}
	
	public Inventory(int quantity, List<Warehouse> wharehouses) {
		super();
		
		this.wharehouses = wharehouses;
		this.quantity = (this.wharehouses.size() > 0) ? 0 : this._calculateQuantity();
	}

	public List<Warehouse> getWharehouses() {
		return wharehouses;
	}
	
	public void setWharehouses(List<Warehouse> wharehouses) {
		this.wharehouses = wharehouses;
	}
	
	
	private int _calculateQuantity() {
		int quantity = 0;
		for (Warehouse warehouse : this.wharehouses) {
			this.quantity += warehouse.quantity;
		}
		
		return quantity;
	}
	
	
}
