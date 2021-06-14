package br.com.blz.testjava.model;

import java.util.UUID;

public class Product {

	private UUID id;
	private String name;
	private Inventory inventory;
	private Boolean isMarketable;

	public Product() {
	}

	public Product(UUID id, String name, Inventory inventory) {
		super();
		this.id = id;
		this.name = name;
		this.inventory = inventory;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	@Override
	public String toString() {
		return "Product [id=" + this.id + ", name=" + this.name + ", inventory=" + this.inventory.toString()
				+ ", isMarketable=" + this.isMarketable + "]";
	}

}
