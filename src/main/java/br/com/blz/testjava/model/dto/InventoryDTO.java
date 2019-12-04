package br.com.blz.testjava.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.blz.testjava.model.Inventory;

public class InventoryDTO {
	private int quantity;
	private List<WarehouseDTO> warehouses;
	
	public InventoryDTO(Inventory inventory) {
		this.quantity = inventory.getQuantity();
		this.warehouses = new ArrayList<WarehouseDTO>();
		this.warehouses.addAll(inventory.getWarehouses().stream().map(WarehouseDTO::new).collect(Collectors.toList()));
	}
	
	public int getQuantity() {
		return quantity;
	}
	public List<WarehouseDTO> getWarehouses() {
		return warehouses;
	}
}
