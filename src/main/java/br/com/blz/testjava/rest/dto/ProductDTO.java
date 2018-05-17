package br.com.blz.testjava.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.blz.testjava.model.Product;

public class ProductDTO {
	@NotNull @Min(1)
	private Integer sku;
	@NotNull @Size(min = 1)
	private String name;
	
	@NotNull
	private InventoryDTO inventory;
	
	private boolean marketable;
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Product product) {
		this.name = product.getName();
		this.sku = product.getSku();
		inventory = new InventoryDTO(product.getInventory());
		this.marketable = inventory.getQuantity() > 0;
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

	public InventoryDTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}

	public boolean isMarketable() {
		return marketable;
	}

	public void setMarketable(boolean marketable) {
		this.marketable = marketable;
	}
	
}
