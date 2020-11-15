package br.com.blz.testjava.dtos;

import java.io.Serializable;
import java.util.List;

public class ProductResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long sku;
	private String name;
	private Inventory inventory;
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


	static class Inventory implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Integer quantity;
		private List<Warehouse> warehouses;
		
		public List<Warehouse> getWarehouses() {
			return warehouses;
		}
		public void setWarehouses(List<Warehouse> warehouses) {
			this.warehouses = warehouses;
		}
		
		public Integer getQuantity() {
			return quantity;
		}
		
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

	}
	
}
