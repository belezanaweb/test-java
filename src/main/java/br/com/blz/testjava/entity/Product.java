package br.com.blz.testjava.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Product {

	@NotNull
	private int sku;
	
	@NotNull
	private String name;
	
	@Valid
	private Inventory inventory;
	
	private Boolean isMarketable;

	public static Product valueOf(int sku, String name, Inventory inventory) {
		return new Product(sku, name, inventory);
	}

	public Product() {}

	public Product(int sku, String name, Inventory inventory) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
	}

	public int getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Boolean getIsMarketable() {
		return this.getInventory().getQuantity() > 0;
	}

}
