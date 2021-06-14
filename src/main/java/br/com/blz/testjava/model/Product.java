package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	private Long sku;
	private String name;
	private Inventory inventory;
	private Boolean isMarketable;

	public Product() {
		
	}

	public Product(Long sku, String name, Inventory inventory) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
	}

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

	public Boolean getIsMarketable() {
		return inventory.getQuantity() > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
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

	@Override
	public String toString() {
		return "Product [id=" + this.sku + ", name=" + this.name + ", inventory=" + this.inventory.toString()
				+ ", isMarketable=" + this.isMarketable + "]";
	}

}
