package br.com.blz.model;

import java.util.List;

public class InventoryModel {

	private Integer quantity;
	
	private List<WarehouseModel> warehouses;
	
	public InventoryModel() {
		super();
	}
	
	public InventoryModel(Integer quantity, List<WarehouseModel> warehouses) {
		super();
		this.quantity = quantity;
		this.warehouses = warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<WarehouseModel> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseModel> warehouses) {
		this.warehouses = warehouses;
	}
}
