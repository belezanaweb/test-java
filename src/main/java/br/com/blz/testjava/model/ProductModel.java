package br.com.blz.testjava.model;

import javax.validation.constraints.NotNull;

public class ProductModel {
	
	@NotNull
    private Integer sku;

    @NotNull
    private String name;

    @NotNull
    private InventoryModel inventory;

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

	public InventoryModel getInventory() {
		return inventory;
	}

	public void setInventory(InventoryModel inventory) {
		this.inventory = inventory;
	}

}
