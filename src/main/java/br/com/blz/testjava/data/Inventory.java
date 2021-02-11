package br.com.blz.testjava.data;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
	
	private Set<Warehouse> warehouses = new HashSet<>();
	
	
	
	public Set<Warehouse> getWarehouses() {
		return warehouses;
	}



	public void setWarehouses(Set<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}



	public Long getQuantity() {
				
		return warehouses.stream().mapToLong(warehouse -> warehouse.getQuantity()).sum();
		

	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (warehouses == null) {
			if (other.warehouses != null)
				return false;
		} else if (!warehouses.equals(other.warehouses))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Inventory [warehouses=" + warehouses + "]";
	}
	
	

}
