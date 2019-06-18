package br.com.blz.testjava.entity;

import javax.validation.constraints.NotNull;

public class Product {
	
	@NotNull
	private Long sku;
	@NotNull
	private String name;
	@NotNull
	private Inventory inventory;
	
	private boolean isMarketable;
	
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
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
	}
	public boolean isMarketable() {
		return isMarketable;
	}
	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
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
