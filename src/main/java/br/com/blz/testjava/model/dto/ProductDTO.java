package br.com.blz.testjava.model.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long sku;
	
	private String name;
	
	private InventoryDTO inventory;
	
	/**
	 * 
	 */
	public ProductDTO() {}
	
	/**
	 * @param sku
	 * @param name
	 * @param inventory
	 */
	public ProductDTO(Long sku, String name, InventoryDTO inventory) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
	}

	/**
	 * @return the sku
	 */
	public Long getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(Long sku) {
		this.sku = sku;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the inventory
	 */
	public InventoryDTO getInventory() {
		return inventory;
	}
	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}
	
}
