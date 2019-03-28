package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<WarehouseDto> warehouses;

	public InventoryDto() {
	}

	public InventoryDto(WarehouseDto... warehouse) {
		warehouses = new ArrayList<>();
		warehouses.addAll(Arrays.asList(warehouse));
	}

	public List<WarehouseDto> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseDto> warehouses) {
		this.warehouses = warehouses;
	}

	public int getQuantity() {
		List<WarehouseDto> warehouses= this.getWarehouses();
		
		if (warehouses != null) {
			return warehouses.parallelStream().mapToInt(WarehouseDto::getQuantity).sum();
		} else {
			return 0;
		}
	}

}
