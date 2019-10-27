/**
 * 
 */
package br.com.blz.testjava.dto;

/**
 * @author ocean
 *
 */
public class ProductDTO {
	
	private Long sku;
	
	private String name;
	
	private InventoryDTO inventory;
	
	private Boolean isMarketable;
	

	public ProductDTO() {
		super();
	}

	public ProductDTO(Long sku, String name) {
		super();
		this.sku = sku;
		this.name = name;
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
		if (inventory == null) {
			inventory = new InventoryDTO();
		}
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(InventoryDTO inventory) {
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
