package br.com.blz.testjava.model;

import java.util.List;

import javax.validation.constraints.NotNull;

public class InventoryModel {
	
	@NotNull
    private List<WarehouseModel> warehouses;

	public List<WarehouseModel> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseModel> warehouses) {
		this.warehouses = warehouses;
	}
	
}
