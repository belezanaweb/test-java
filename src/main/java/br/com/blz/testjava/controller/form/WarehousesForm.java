package br.com.blz.testjava.controller.form;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.Warehouses;

public class WarehousesForm {
	
	
	@JsonProperty("warehouses")
	private ArrayList<WarehouseForm> warehousesForm = new ArrayList<WarehouseForm>();

	
	public void addWarehouseForm(WarehouseForm warehouse) {
		this.warehousesForm.add(warehouse);
	}
	
	public Warehouses converterWharehouses() {
		//warehouses.setWarehouses(warehouses);
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>(); 
		for (WarehouseForm whF : this.warehousesForm) {
			warehouses.add(whF.converteWarehouse());
		}
		
		return new Warehouses(warehouses);
	}

}
