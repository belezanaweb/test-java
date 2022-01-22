package br.com.blz.testjava.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
	String sku;
	String name;
	InventoryDTO inventory;
	Boolean isMarketable;
	
	public ProductDTO(String sku, String name, InventoryDTO inventory) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
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
	public InventoryDTO getInventory() {
		return inventory;
	}
	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}
	@Override
	public String toString() {
		return "Produto DTO " + this.name + " -  " + this.sku + " \n " + this.inventory.toString();
	}
	
	
	
	
}
