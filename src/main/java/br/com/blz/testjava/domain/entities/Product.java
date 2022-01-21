package br.com.blz.testjava.domain.entities;

public class Product {
	String sku;
	String name;
	Inventory inventory;
	Boolean isMarketable;
	
	public Product(String sku, String name, Inventory inventory) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = (this.inventory.quantity > 0);
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
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
		return isMarketable;
	}

}
