package br.com.blz.testjava.model.vo;

import java.io.Serializable;
import java.util.List;

public class InventoryVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer quantity;
	private List<WarehouseVO> warehouses;
	
	
	/**
	 * @param quantity
	 * @param warehouses
	 */
	public InventoryVO(Integer quantity, List<WarehouseVO> warehouses) {
		this.quantity = quantity;
		this.warehouses = warehouses;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the warehouses
	 */
	public List<WarehouseVO> getWarehouses() {
		return warehouses;
	}
	/**
	 * @param warehouses the warehouses to set
	 */
	public void setWarehouses(List<WarehouseVO> warehouses) {
		this.warehouses = warehouses;
	}
	
}
