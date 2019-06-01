package br.com.blz.testjava.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe específica para indicar a quantidade de um determinado produto em um Warehouse.
 * Também é específica para ser usada na contagem da quantidade desse produto em estoque.
 *   A quantidade em estoque é a soma das quantidades em cada Warehouse.
 */

public class Inventory {
	
	private Integer quantity;
	
	
	private List<Warehouse> warehouses;
	

	public Inventory() {
		warehouses = new ArrayList<>();
	}		

	

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((warehouses == null) ? 0 : warehouses.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (warehouses == null) {
			if (other.warehouses != null)
				return false;
		} else if (!warehouses.equals(other.warehouses))
			return false;
		return true;
	}
	
	
	
	
}
