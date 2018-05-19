package br.com.blz.testjava.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Inventory {

	@Null
	private Integer quantity;
	@NotNull
	private List<Warehouse> warehouses;
	
	Inventory(){
	}

	public Inventory(Integer quantity, List<Warehouse> warehouses){
		this.quantity = quantity;
		this.warehouses = warehouses;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
}
