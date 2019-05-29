package br.com.blz.testjava.dto;

public class ProductDTO {

	private Long sku;
	private String name;
	private InventoryDTO inventory;
	private Boolean isMarketable;

	/***
	No args constructor for
	use in serialization**/

	public ProductDTO() {
	}

	/**
	 * 
	 * @param inventory
	 * @param name
	 * @param isMarketable
	 * @param sku
	 */
	public ProductDTO(Long sku, String name, InventoryDTO inventory, Boolean isMarketable) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
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

	public Boolean getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
}
