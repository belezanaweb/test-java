package br.com.blz.testjava.entity;

import java.util.List;

/**
 * Classe referente ao objeto inventory
 * @author jmestre
 */
public class Inventory {

	private Integer quantity;
	private List<Warehouses> warehouses;
	
	public Inventory(List<Warehouses> warehouses) {
		super();
		this.warehouses = warehouses;
	}

	public List<Warehouses> getWarehouses() {
		return warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
