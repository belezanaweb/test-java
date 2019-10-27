/**
 * 
 */
package br.com.blz.testjava.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.model.Warehouses;

/**
 * @author ocean
 *
 */
public class InventoryDTO {
	
	private Long quantity;
	
	private List<Warehouses> warehouses;	
	

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the warehouses
	 */
	public List<Warehouses> getWarehouses() {
		if (warehouses == null) {
			warehouses = new ArrayList<Warehouses>();
		}
		return warehouses;
	}

	/**
	 * @param warehouses the warehouses to set
	 */
	public void setWarehouses(List<Warehouses> warehouses) {
		this.warehouses = warehouses;
	}

}
