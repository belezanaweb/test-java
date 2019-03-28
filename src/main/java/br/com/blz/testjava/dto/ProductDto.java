package br.com.blz.testjava.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long sku;
	private String name;
	private InventoryDto inventory;

	public ProductDto(long sku, String name) {
		this.sku = sku;
		this.name = name;
	}

	public ProductDto() {
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

	public InventoryDto getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDto inventory) {
		this.inventory = inventory;
	}
	
	@JsonProperty("isMarketable")
	public boolean isMarketable() {
		InventoryDto inventoryDto = getInventory();
		if (inventoryDto == null) {
			return false;
		} else {
			return inventoryDto.getQuantity() > 0;
		}
	}

	@Override
	public int hashCode() {
		final long prime = 31;
		long result = 1;
		result = prime * result + sku;
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDto other = (ProductDto) obj;
		if (sku != other.sku)
			return false;
		return true;
	}
	
}
