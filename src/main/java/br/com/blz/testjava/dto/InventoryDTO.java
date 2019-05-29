package br.com.blz.testjava.dto;

import java.util.List;

public class InventoryDTO {

	private Long quantity;
	private List<WarehouseDTO> warehouses = null;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public InventoryDTO() {
	}

	/**
	 * 
	 * @param warehouses
	 * @param quantity
	 */
	public InventoryDTO(Long quantity, List<WarehouseDTO> warehouses) {
		super();
		this.quantity = quantity;
		this.warehouses = warehouses;
	}

	public Long getQuantity() {
		quantity = 0L;
		for (WarehouseDTO warehouseDTO : warehouses) {
			quantity = quantity+warehouseDTO.getQuantity();
		}
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public List<WarehouseDTO> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseDTO> warehouses) {
		this.warehouses = warehouses;
	}

}
