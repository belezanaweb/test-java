package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.model.Warehouse;

public class InventoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Warehouse> warehouses = new ArrayList<>();

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
}
