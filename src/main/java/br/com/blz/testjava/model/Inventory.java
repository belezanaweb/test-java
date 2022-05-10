package br.com.blz.testjava.model;

public class Inventory{

	private Warehouses[] warehouses;
	private Integer quantity;

	public Inventory(Warehouses[] warehouses, Integer quantity) {
		this.warehouses = warehouses;
		this.quantity = null;
	}
	public Warehouses[] getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Warehouses[] warehouses) {
		this.warehouses = warehouses;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}