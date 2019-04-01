package br.com.blz.testjava.model.dto;

import java.io.Serializable;
import java.util.List;

public class InventoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<WarehouseDTO> warehouses;
	
	/**
	 * 
	 */
	public InventoryDTO() {}

	/**
	 * @param warehouses
	 */
	public InventoryDTO(List<WarehouseDTO> warehouses) {
		this.warehouses = warehouses;
	}

	/**
	 * @return the warehouses
	 */
	public List<WarehouseDTO> getWarehouses() {
		return warehouses;
	}

	/**
	 * @param warehouses the warehouses to set
	 */
	public void setWarehouses(List<WarehouseDTO> warehouses) {
		this.warehouses = warehouses;
	}
	

}
