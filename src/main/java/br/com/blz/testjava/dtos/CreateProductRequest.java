package br.com.blz.testjava.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class CreateProductRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "SKU é obrigatório")
	private Long sku;
	private String name;
	private CreateProductInventory inventory;
	
	
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateProductInventory getInventory() {
		return inventory;
	}
	public void setInventory(CreateProductInventory inventory) {
		this.inventory = inventory;
	}

	
	public static class CreateProductInventory  implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private List<Warehouse> warehouses;
		
		public List<Warehouse> getWarehouses() {
			return warehouses;
		}
		public void setWarehouses(List<Warehouse> warehouses) {
			this.warehouses = warehouses;
		}
		
	}
}
