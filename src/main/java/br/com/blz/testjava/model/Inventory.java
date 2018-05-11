package br.com.blz.testjava.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

public final class Inventory {

	private Integer quantity;
	@NotNull
	private List<Warehouse> warehouses = new ArrayList<>();

	public Inventory(final Integer quantity, final List<Warehouse> warehouses) {
		this.quantity = quantity;
		this.warehouses = checkNotNull(warehouses);
	}
	
	public Inventory() {
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(final List<Warehouse> warehouses) {
		this.warehouses.clear();
		this.warehouses = warehouses;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("quantity", quantity)
				.add("warehoses", warehouses)
				.toString();
	}
}
