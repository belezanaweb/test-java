package br.com.blz.testjava.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Warehouses {
	
	private long quantity;
	
	public Warehouses(ArrayList<Warehouse> warehouses) {
		this.warehouses = warehouses;
		this.setQuantity();
	}
	
	@JsonProperty("warehouses")
	private ArrayList<Warehouse> warehouses;

	public long getQuantity() {
		return quantity;
	}

	private void setQuantity() {
		for ( Warehouse wh : warehouses) {
			this.quantity += wh.getQuantity();
		}
		
	}

	public void setWarehouses(ArrayList<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
