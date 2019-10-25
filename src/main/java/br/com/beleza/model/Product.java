package br.com.beleza.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	
	private Integer sku;
	private String name;
	private Inventory inventory;
	private boolean isMarketable;

	public Product() {
		this.inventory = new Inventory();
	}
	
	public Product(Integer sku) {
		this();
		this.sku = sku;
	}
 	
	public Integer getSku() {
		return sku;
	}
	
	public void setSku(Integer sku) {
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
	
	@JsonProperty("isMarketable")
	public boolean isMarketable() {
		this.isMarketable = this.inventory.getQuantity() > 0;
		return isMarketable;	
	}
}
