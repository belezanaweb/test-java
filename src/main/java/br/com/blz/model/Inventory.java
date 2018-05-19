package br.com.blz.model;

import java.util.Objects;
import java.util.Set;

public class Inventory {
	
	Set<Warehouse> warehouses;

	public Set<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Set<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public Integer getQuantity() {
		if( !Objects.isNull(warehouses) && !warehouses.isEmpty() ) {
			return warehouses.stream().mapToInt((warehouse) -> warehouse.getQuantity()).sum();
		}
		return null;
	}
}
