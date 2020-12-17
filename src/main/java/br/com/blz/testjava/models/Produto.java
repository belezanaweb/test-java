package br.com.blz.testjava.models;

import java.io.Serializable;

public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}
	
	private long sku = 0;
	private String name;
	private Inventory inventory;
	private boolean isMarketable = false;

	public long getSku() {
		return sku;
	}
	public void setSku(long sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		if(inventory.getQuantity() > 0) {
			setMarketable(true);
		}
	}
	public boolean isMarketable() {
		return isMarketable;
	}
	private void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	
}
