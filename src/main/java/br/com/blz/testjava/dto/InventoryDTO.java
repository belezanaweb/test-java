package br.com.blz.testjava.dto;

import java.util.List;

import br.com.blz.testjava.model.WareHouse;

public class InventoryDTO {

	private Long quantity;

	private List<WareHouse> warehouses;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public List<WareHouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WareHouse> warehouses) {
		this.warehouses = warehouses;
	}

	
}
