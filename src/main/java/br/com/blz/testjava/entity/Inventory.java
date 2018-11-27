package br.com.blz.testjava.entity;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Inventory {

	private Integer quantity;
	List<Warehouse> warehouses;

	public Inventory() {}

	public Inventory(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
		addQuantity();
	}

	public static Inventory valueOf(List<Warehouse> warehouses) {
		return new Inventory(warehouses);
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void addQuantity() {
		Optional<Integer> quantity = this.getWarehouses().stream()
				.map(Warehouse::getQuantity)
				.reduce(Integer::sum);
		
		this.quantity = quantity.get();

	}
	

}
