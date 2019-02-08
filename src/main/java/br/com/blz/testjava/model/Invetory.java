package br.com.blz.testjava.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Invetory {

	private int quantity;
	private Set<Warehouse> warehouses;

	public Invetory() {}

	public Invetory(Set<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public Invetory(int quantity, Set<Warehouse> warehouses) {
		this(warehouses);
		this.quantity = quantity;
	}

	public int getQuantity() {
		return Optional.ofNullable(this.warehouses)
				.orElse(new HashSet<Warehouse>())
				.stream()
				.mapToInt(Warehouse::getQuantity)
				.sum();
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Set<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Set<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
