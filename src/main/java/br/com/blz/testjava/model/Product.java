package br.com.blz.testjava.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Product {
	
	@NotNull(message = "Product's sku can't be null")
	private Long sku;
	
	@NotEmpty(message = "Product's name can't be empty")
	private String name;
	
	@NotNull(message = "Product's inventory can't be null")
	private Inventory inventory;
	
	private Boolean isMarketable;

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
		isMarketable = false;
		
		if (inventory != null && inventory.getQuantity() > 0) {
			isMarketable = true;
		}
		
		return isMarketable;
	}

	
}
