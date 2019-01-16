package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 2019010901L;
	
	public Inventory() {
		super();
	}
	
	public Inventory(List<WarehouseProduct> warehouses) {
		super();
		this.warehouses = warehouses;
	}

	@JsonProperty("warehouses")
	private List<WarehouseProduct> warehouses;
	
	private Integer quantity;
	
	public List<WarehouseProduct> getListProductWarehouse() {
		return warehouses;
	}
	
	public void setListProductWarehouse(List<WarehouseProduct> warehouses) {
		this.warehouses = warehouses;
	}
	
	@JsonProperty(value = "quantity", required=false)
	@Transactional
	public Integer getQuantity() {
		if (this.quantity == null) {
			quantity = 0;
			for (WarehouseProduct productWarehouseFacade : warehouses) {
				this.quantity += productWarehouseFacade.getQuantity();
			}
		}
		return this.quantity;
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

	@Override
	public String toString() {
		return "Inventory [warehouses=" + warehouses + ", quantity=" + getQuantity() + "]";
	}
}
