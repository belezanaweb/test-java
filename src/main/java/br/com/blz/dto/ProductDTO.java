package br.com.blz.dto;

import java.util.Collection;
import java.util.List;

import br.com.blz.model.Warehouse;

public class ProductDTO {
	
	private String name;
	
	private long sku;
	
	
	private InventoryVO inventory;
	
	private boolean isMarketable;
	
	public ProductDTO(String name,  long sku,Long quantity,boolean isMarketable) {
		this.name=name;
		this.sku=sku;
		this.isMarketable=isMarketable;
		this.inventory=new InventoryVO();
		this.inventory.setQuantity(quantity);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getSku() {
		return sku;
	}


	public void setSku(long sku) {
		this.sku = sku;
	}


	public InventoryVO getInventory() {
		return inventory;
	}


	public void setInventory(InventoryVO inventory) {
		this.inventory = inventory;
	}


	public boolean isMarketable() {
		return isMarketable;
	}


	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	
	
	

}
