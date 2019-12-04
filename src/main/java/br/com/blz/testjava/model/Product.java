package br.com.blz.testjava.model;

public class Product {
	private Long sku;
	private String name;
	private Inventory inventory;
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
	public boolean isMarketable() {
		return this.inventory.getQuantity() > 0;
	}
}
