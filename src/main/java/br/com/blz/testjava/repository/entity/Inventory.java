package br.com.blz.testjava.repository.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Warehouse> warehouses;

	public Inventory() {
	}

	public Inventory(Warehouse... warehouse) {
		warehouses = new ArrayList<>();
		warehouses.addAll(Arrays.asList(warehouse));
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
