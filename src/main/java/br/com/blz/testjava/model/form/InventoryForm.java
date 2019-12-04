package br.com.blz.testjava.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Warehouse;

public class InventoryForm {
	@NotNull
	private List<WarehouseForm> warehouses;

	public void setWarehouses(List<WarehouseForm> warehouses) {
		this.warehouses = warehouses;
	}

	public Inventory convert() {
		List<Warehouse> ws = new ArrayList<Warehouse>();
		ws.addAll(this.warehouses.stream().map(w -> new Warehouse(w.getLocality(), w.getQuantity(), w.getType())).collect(Collectors.toList()));
		return new Inventory(ws);
	}
}
