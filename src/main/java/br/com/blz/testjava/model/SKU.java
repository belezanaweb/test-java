package br.com.blz.testjava.model;

public class SKU {

	private Integer sku;
	private String name;
	private Inventory inventory;
	private Boolean isMarketable;
	
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

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Boolean getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

}