package br.com.blz.testjava.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryDTO {
	List<WarehouseDTO> warehouses;
	int quantity;
	
	
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

	public InventoryDTO(List<WarehouseDTO> warehouses) {
		super();
		this.warehouses = warehouses;
	}
	
	public InventoryDTO() {
		super();
		
	}

	public List<WarehouseDTO> getWharehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseDTO> warehouses) {
		this.warehouses = warehouses;
	}
	
	@Override
	public String toString() {
		String whs = "";
		for (WarehouseDTO warehouseDTO : warehouses) {
			whs += warehouseDTO.toString() + "\n";
		}
		return whs;
		
	}
}
