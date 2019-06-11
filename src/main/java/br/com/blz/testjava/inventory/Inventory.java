package br.com.blz.testjava.inventory;

import java.util.List;

import br.com.blz.testjava.warehouse.Warehouse;

public class Inventory {

	private Integer quantity;
	
	private List<Warehouse> warehouses;

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

}
