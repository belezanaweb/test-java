package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Product {

	private int sku;
	private String name;
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
		addMarketable();
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
		return isMarketable;
	}

	public void addMarketable() {
		this.isMarketable = this.getInventory().getQuantity() > 0;
	}
}
