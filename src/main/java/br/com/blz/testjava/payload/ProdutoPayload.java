package br.com.blz.testjava.payload;

public class ProdutoPayload {
	
	private Integer sku;	
	private String name;
	private InventoryPayload inventory;
	
	
	public boolean isMarketable() {
		if(inventory != null && inventory.getQuantity() > 0) {
			return true;
		}
		return false;
	}
	
	
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
	
	public InventoryPayload getInventory() {
		return inventory;
	}
	
	public void setInventory(InventoryPayload inventory) {
		this.inventory = inventory;
	}
	
	
}
