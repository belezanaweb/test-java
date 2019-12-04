package br.com.blz.testjava.model.dto;

import br.com.blz.testjava.model.Product;

public class ProductDTO {
	private Long sku;
	private String name;
	private InventoryDTO inventory;
	private boolean isMarketable;
	public Long getSku() {
		return sku;
	}
	public String getName() {
		return name;
	}
	public InventoryDTO getInventory() {
		return inventory;
	}
	
	public ProductDTO(Product product) {
		this.sku = product.getSku();
		this.name = product.getName();
		this.inventory = new InventoryDTO(product.getInventory());
		this.isMarketable = product.isMarketable();
	}
	public boolean isMarketable() {
		return isMarketable;
	}
}
