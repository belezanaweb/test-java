package br.com.blz.testjava.model.form;

import javax.validation.constraints.NotNull;

import br.com.blz.testjava.model.Product;

public class ProductForm {
	@NotNull
	private Long sku;
	@NotNull
	private String name;
	@NotNull
	private InventoryForm inventory;
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setInventory(InventoryForm inventory) {
		this.inventory = inventory;
	}
	
	public Product convert() {
		return new Product(this.sku, this.name, this.inventory.convert());
	}
}
