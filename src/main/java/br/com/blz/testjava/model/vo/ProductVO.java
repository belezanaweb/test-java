package br.com.blz.testjava.model.vo;

import java.io.Serializable;

public class ProductVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long sku;
	private String name;
	private InventoryVO inventory;
	private Boolean isMarketable;
	
	/**
	 * @param sku
	 * @param name
	 * @param inventory
	 * @param isMarketable
	 */
	public ProductVO(Long sku, String name, InventoryVO inventory, Boolean isMarketable) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
	}
	
	/**
	 * 
	 */
	public ProductVO() {}

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
	public InventoryVO getInventory() {
		return inventory;
	}
	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(InventoryVO inventory) {
		this.inventory = inventory;
	}
	/**
	 * @return the isMarketable
	 */
	public Boolean getIsMarketable() {
		return isMarketable;
	}
	/**
	 * @param isMarketable the isMarketable to set
	 */
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	
}
