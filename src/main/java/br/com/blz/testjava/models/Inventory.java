package br.com.blz.testjava.models;

import java.io.Serializable;
import java.util.Vector;

public class Inventory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Inventory() {
		// TODO Auto-generated constructor stub
	}
	
	private long quantity;
	private Vector<WareHouse> warehouses = new Vector<WareHouse>();
	
	public long getQuantity() {
		return quantity;
	}
	
	private void addQuantity(long quantity) {
		this.quantity += quantity;
	}
	
	public Vector<WareHouse> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(Vector<WareHouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public void addWarehouse(WareHouse warehouse) {
		this.warehouses.add(warehouse);
		addQuantity(warehouse.getQuantity());
	}
	
}
