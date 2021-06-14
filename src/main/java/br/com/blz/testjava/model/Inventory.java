package br.com.blz.testjava.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {
	private Integer quantity;
	private List<Warehouse> warehouses;

	public Inventory() {
	}

	public Inventory(List<Warehouse> warehouses) {
		super();
		this.warehouses = warehouses;
	}

	public Integer getQuantity() {
		return warehouses.stream().mapToInt(warehouse -> warehouse.getQuantity()).sum();
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

	@Override
	public String toString() {
		return "Inventory [quantity=" + this.quantity + ", warehouses=" + this.warehouses + "]";
	}

}
