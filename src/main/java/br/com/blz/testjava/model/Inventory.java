package br.com.blz.testjava.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class Inventory {

	private Long quantity;
	
	@NotEmpty(message = "Inventory's warehouses can't be empty")
	private List<Warehouse> warehouses;
	
	

	public Inventory(@NotEmpty(message = "Inventory's warehouses can't be empty") List<Warehouse> warehouses) {
		super();
		this.warehouses = warehouses;
	}

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getQuantity() {
		quantity = 0L;
		if (warehouses != null && !warehouses.isEmpty()) {
		  quantity = warehouses.stream().mapToLong(Warehouse::getQuantity).sum();
		}
		return quantity;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
}
