package br.com.blz.testjava.entity;

import java.util.Set;

public class Inventory {
	
	private Integer quantity;
	private Set<Warehouse> warehouses;
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Set<Warehouse> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(Set<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	
	

}
