package br.com.blz.testjava.model;

import org.springframework.stereotype.Component;

@Component
public class Produto {

	private long sku;
	private String name;
	private Warehouses inventory;
	private Boolean isMarketable;

	public Produto() {
	
	}
	
	public Produto(
			Long sku, 
			String name,
			Warehouses inventory) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.setIsMarketable();
	}

	public long getSku() {
		return sku;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Warehouses getInventory() {
		return inventory;
	}

	public void setInventory(Warehouses inventory) {
		this.inventory = inventory;
	}

	public Boolean getIsMarketable() {
		return isMarketable;
	}

	private void setIsMarketable() {
		this.isMarketable = (this.inventory.getQuantity() > 0 );
	}

}
