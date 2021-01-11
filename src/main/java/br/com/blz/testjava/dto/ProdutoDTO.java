package br.com.blz.testjava.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long sku;
	private String name;
	private InventoryDTO inventory;
	private Boolean isMarketable;
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
	public InventoryDTO getInventory() {
		return inventory;
	}
	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}
	public Boolean getIsMarketable() {
		return isMarketable;
	}
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

}
