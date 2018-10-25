package br.com.blz.testjava.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer sku;
	private String name;
	private InventoryDTO inventory = new InventoryDTO();
	
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
	
	public InventoryDTO getInventory() {
		return inventory;
	}
	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}
	
}
