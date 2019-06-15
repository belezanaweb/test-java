package br.com.blz.testjava.model;

/**
 * Class that represents the Product entity.
 * 
 * @author Andre Barroso
 *
 */
public class Product {

	/**
	 * Product SKU.
	 */
	private Long sku;
	
	/**
	 * Product name.
	 */
	private String name;
	
	/**
	 * Product inventory.
	 */
	private Inventory inventory;

	/**
	 * Gets sku
	 * @return sku
	 */
	public Long getSku() {
		return sku;
	}

	/**
	 * Sets sku
	 * @param sku Product sku
	 */
	public void setSku(Long sku) {
		this.sku = sku;
	}

	/**
	 * Gets name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name
	 * @param name Product name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets inventory
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Sets inventory
	 * @param inventory Product inventory
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Method responsible for defining whether a product is negotiable.
	 * 
	 * @return isMarketable
	 */
	public boolean isMarketable() {
		
		boolean isMarketable = false;
		
		if(this.inventory != null) {
			isMarketable = this.inventory.getQuantity() > 0L;
		}
		return isMarketable;
	}

}
