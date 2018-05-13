package br.com.blz.testjava.model;

import java.io.Serializable;

public class Product implements Serializable {
	
	private String sku;
	private String name;
	private boolean isMarketable;
	private Inventory inventory;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public boolean isMarketable() {
		if (this.inventory == null) {
			this.isMarketable = false;
		} else {
			if (this.inventory.getQuantity() > 0) {
				this.isMarketable = true;
			} else {
				this.isMarketable = false;
			}
		}
		return isMarketable;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
}
