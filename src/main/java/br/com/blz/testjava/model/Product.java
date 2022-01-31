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
	
	
	

	public Product(@NotNull(message = "Product's sku can't be null") Long sku,
			@NotEmpty(message = "Product's name can't be empty") String name,
			@NotNull(message = "Product's inventory can't be null") Inventory inventory, Boolean isMarketable) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
	}
	
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
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
		isMarketable = false;
		
		if (inventory != null && inventory.getQuantity() > 0) {
			isMarketable = true;
		}
		
		return isMarketable;
	}

	
}
