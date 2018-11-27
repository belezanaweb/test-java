package br.com.blz.testjava.entity;

import java.util.List;

import javax.validation.Valid;

public class Inventory {

	private Integer quantity;
	
	@Valid
	private List<Warehouse> warehouses;

	public Inventory() {
	}

	public Inventory(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public static Inventory valueOf(List<Warehouse> warehouses) {
		return new Inventory(warehouses);
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public Integer getQuantity() {
		return this.getWarehouses().stream()
						.map(Warehouse::getQuantity)
						.reduce(Integer::sum)
						.get();
	}

}
