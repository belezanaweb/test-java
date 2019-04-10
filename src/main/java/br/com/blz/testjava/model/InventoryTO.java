package br.com.blz.testjava.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryTO {
	
	@JsonProperty("warehouses")
	private List<WarehouseTO> warehousesTO;

	public InventoryTO() {

	}

	public InventoryTO(List<WarehouseTO> warehousesTO) {
		this.warehousesTO = warehousesTO;
	}

	public int getQuantity() {
		return warehousesTO.stream().mapToInt(warehouseto -> warehouseto.getQuantity()).sum();
	}

	public List<WarehouseTO> getWarehousesTO() {
		return warehousesTO;
	}

	public void setWarehousesTO(List<WarehouseTO> warehousesTO) {
		this.warehousesTO = warehousesTO;
	}

}
