package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {
	

	private Long quantity;
	
	private List<WareHouses> warehouses;

	public Long getQuantity() {
		
		if(warehouses.isEmpty()) {
			
			this.quantity = 0L;
			
		}else{
			
			this.quantity = warehouses.stream().mapToLong(WareHouses::getQuantity).sum();
			
		}
		
		return quantity;
	}

	public void setQuantity(Long quantity) {

			this.quantity = quantity;
		
	}

	public List<WareHouses> getWarehouses() {
		
		return warehouses;
		
	}

	public void setWarehouses(List<WareHouses> warehouses) {
		
		this.warehouses = warehouses;
		
	}
	
}
