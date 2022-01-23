package br.com.blz.testjava.data.dto;




public class ProductDTO {
	private String sku;
	private String name;
	private InventoryDTO inventory;
	private Boolean isMarketable;
	
	public ProductDTO() {
		super();
	}
	
	public ProductDTO(String sku, String name, InventoryDTO inventory, Boolean isMarketable) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
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
	public Boolean getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
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
