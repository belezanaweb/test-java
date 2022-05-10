package br.com.blz.testjava.model;



public class Product {
	
	private Long sku;
	private String name;
	private Inventory inventory;
	private Boolean isMarketable;
	
	public Product(Long sku, String name, Inventory inventory, Boolean isMarketable) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = false;
	}
	
	public Boolean getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
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
}
