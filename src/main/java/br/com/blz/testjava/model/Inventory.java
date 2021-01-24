package br.com.blz.testjava.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private Long quantity;

	private List<WareHouse> warehouses = new ArrayList<WareHouse>();

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public List<WareHouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WareHouse> warehouses) {
		this.warehouses = warehouses;
	}

}
