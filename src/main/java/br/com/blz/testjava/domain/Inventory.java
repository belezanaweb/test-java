package br.com.blz.testjava.domain;

import java.util.List;

import br.com.blz.testjava.domain.Warehouse;

public class Inventory {
	
	private Long quantity;
	
	private List<Warehouse> warehouses;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
