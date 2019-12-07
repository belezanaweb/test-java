package br.com.blz.testjava.dto;

import java.util.ArrayList;

public class InventoryDTO {
	private int quantity;
	ArrayList<Warehouses> warehouses;

	public ArrayList<Warehouses> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(ArrayList<Warehouses> warehouses) {
		this.warehouses = warehouses;
	}

	// Getter Methods
	public int getQuantity() {
		return quantity;
	}

	// Setter Methods
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
