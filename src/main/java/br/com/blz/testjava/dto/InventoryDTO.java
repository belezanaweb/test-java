package br.com.blz.testjava.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.model.WareHouse;

public class InventoryDTO {

	private Long quantity;

	private List<WareHouseDTO> warehouses = new ArrayList<WareHouseDTO>();;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public List<WareHouseDTO> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WareHouseDTO> warehouses) {
		this.warehouses = warehouses;
	}

	

	
}
