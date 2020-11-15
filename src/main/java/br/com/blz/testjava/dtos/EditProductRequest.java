package br.com.blz.testjava.dtos;

import java.io.Serializable;
import java.util.List;

public class EditProductRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private EditProductInventory inventory;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EditProductInventory getInventory() {
		return inventory;
	}
	public void setInventory(EditProductInventory inventory) {
		this.inventory = inventory;
	}

	
	public static class EditProductInventory implements Serializable {
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
