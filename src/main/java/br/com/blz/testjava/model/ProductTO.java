package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTO {

	private int sku;
	private String name;

	@JsonProperty("inventory")
	private InventoryTO inventoryTo;

	public ProductTO() {

	}

	public ProductTO(int sku, String name) {
		super();
		this.sku = sku;
		this.name = name;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InventoryTO getInventoryTo() {
		return inventoryTo;
	}

	public void setInventoryTo(InventoryTO inventoryTo) {
		this.inventoryTo = inventoryTo;
	}

	@JsonProperty("isMarketable")
	public boolean isMarketable() {
		return inventoryTo != null && inventoryTo.getQuantity() > 0;
	}

}
