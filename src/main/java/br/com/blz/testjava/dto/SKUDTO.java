package br.com.blz.testjava.dto;

public class SKUDTO {

	private int sku;

	private boolean isMarketable;

	private String name;

	InventoryDTO Inventory;

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InventoryDTO getInventory() {
		return Inventory;
	}

	public void setInventory(InventoryDTO inventory) {
		Inventory = inventory;
	}

}
