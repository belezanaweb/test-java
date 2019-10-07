package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventory implements Serializable {
	private static final long serialVersionUID = -1602777753260036553L;
	private List<Warehouse> warehouses;
	public Long getQuantity() {
		if(warehouses == null) {
			return Long.valueOf(0);
		}
		Long toReturn = Long.valueOf(0);
		for(Warehouse warehouse : warehouses) {
			if(warehouse.getQuantity() != null) {
				toReturn = Long.sum(toReturn, warehouse.getQuantity());
			}
		}
		return toReturn;
	}
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
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
	public static Inventory build() {
		return new Inventory();
	}
	public Inventory withWarehouse(ArrayList<Warehouse> warehouses) {
		this.warehouses = warehouses;
		return this;
	}
	public Inventory withAddWarehouse(Warehouse warehouses) {
		if(this.warehouses == null) {
			this.warehouses = new ArrayList<>();
		}
		this.warehouses.add(warehouses);
		return this;
	}
}
