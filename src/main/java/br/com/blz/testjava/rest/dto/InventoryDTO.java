package br.com.blz.testjava.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.blz.testjava.model.Warehouse;

public class InventoryDTO {
	@NotNull
	private List<WarehouseDTO> warehouses;
	private int quantity;
	
	public InventoryDTO() {
		
	}
	
	public InventoryDTO(List<Warehouse> warehouses) {
		this.warehouses = new ArrayList<WarehouseDTO>();
		fillQuantity(warehouses);
		fillWareHouses(warehouses);
	}

	private void fillQuantity(List<Warehouse> warehouses) {
		this.quantity = warehouses.stream().map(w-> w.getQuantity()).reduce(Integer::sum).orElse(0);
	}

	private void fillWareHouses(List<Warehouse> warehouses) {
		warehouses.forEach((w)-> {
			this.warehouses.add(new WarehouseDTO(w));
			});
	}
	

	public List<WarehouseDTO> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseDTO> warehouses) {
		this.warehouses = warehouses;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
