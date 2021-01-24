package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.Inventory;

public class ProductDTO {

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	public Inventory getIventory() {
		return iventory;
	}

	public void setIventory(Inventory iventory) {
		this.iventory = iventory;
	}

	private Long sku;
	private String name;
	private boolean isMarketable;
	private Inventory iventory;

	public ProductDTO(Long sku, String name, boolean isMarketable, Inventory iventory) {
		super();
		this.sku = sku;
		this.name = name;
		this.isMarketable = isMarketable;
		this.iventory = iventory;
	}

}
