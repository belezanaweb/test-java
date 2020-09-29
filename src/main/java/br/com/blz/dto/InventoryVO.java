package br.com.blz.dto;

import java.util.Collection;
import java.util.List;

import br.com.blz.model.Warehouse;

public class InventoryVO {
	
	private Long quantity;
	
	private List<Warehouse> warehouses;

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	

}
