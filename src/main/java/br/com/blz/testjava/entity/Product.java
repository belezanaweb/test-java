package br.com.blz.testjava.entity;

public class Product {
	private Long sku;
	private String name;
	private String inventory;

	public Product(Long sku, String name, String inventory) {

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

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
}
