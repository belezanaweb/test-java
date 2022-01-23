package br.com.blz.testjava.data.dto;

import java.util.List;

public class InventoryDTO {
	List<WarehouseDTO> warehouses;
	int quantity;

	public InventoryDTO() {
		super();		
	}

	public InventoryDTO(List<WarehouseDTO> warehouses, int quantity) {
		super();
		this.warehouses = warehouses;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<WarehouseDTO> getWharehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseDTO> warehouses) {
		this.warehouses = warehouses;
	}
	
	@Override
	public String toString() {
		String warehouseString = "";
		for (WarehouseDTO warehouseDTO : warehouses) {
			warehouseString += warehouseDTO.toString() + "\n";
		}
		return warehouseString;
		
	}
}
