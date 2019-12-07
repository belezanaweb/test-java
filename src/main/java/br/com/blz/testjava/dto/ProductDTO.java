package br.com.blz.testjava.dto;

public class ProductDTO {
	private int sku;
	private String name;
	InventoryDTO InventoryObject;
	private boolean isMarketable;
	
	// Getter Methods

	public int getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public InventoryDTO getInventory() {
		return InventoryObject;
	}

	public boolean getIsMarketable() {
		return isMarketable;
	}

	// Setter Methods

	public void setSku(int sku) {
		this.sku = sku;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInventory(InventoryDTO inventoryObject) {
		this.InventoryObject = inventoryObject;
	}

	public void setIsMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
}
