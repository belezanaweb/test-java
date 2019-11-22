package br.com.belezaNaWeb.javaTest.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class Inventory implements Serializable {

	@ApiModelProperty(value = "Product Inventory Quantity")
	private Integer quantity;

	@ApiModelProperty(value = "Product Inventory WhareHouses")
	private List<Warehouse> warehouses;

	private static final long serialVersionUID = 1L;
	

	public Inventory() {
	}

	public Inventory(List<Warehouse> wharehouses) {
		this.warehouses = wharehouses;
	}

	public Integer getQuantity() {
		if (warehouses != null && !warehouses.isEmpty()) {
			quantity = warehouses.stream()
					.mapToInt(Warehouse::getQuantity)
					.sum();
		} else {
			quantity = 0;
		}
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(List<Warehouse> wharehouses) {
		this.warehouses = wharehouses;
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
		return "Inventory [quantity=" + quantity + ", warehouses=" + warehouses + "]";
	}

}
