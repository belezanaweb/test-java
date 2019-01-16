package br.com.blz.testjava.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.blz.testjava.model.enumeration.WarehouseType;

public class WarehouseProduct implements Serializable {
	
	private static final long serialVersionUID = 2019010901L;
	
	public WarehouseProduct () {
		super();
	}
	
	public WarehouseProduct(String locality, Integer quantity, WarehouseType warehouseType) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.warehouseType = warehouseType;
	}

	@JsonProperty("locality")
	private String locality;
	
	@JsonProperty("quantity")
	private Integer quantity;
	
	@JsonProperty("type")
	private WarehouseType warehouseType;

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public WarehouseType getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(WarehouseType warehouseType) {
		this.warehouseType = warehouseType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locality == null) ? 0 : locality.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((warehouseType == null) ? 0 : warehouseType.hashCode());
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
		WarehouseProduct other = (WarehouseProduct) obj;
		if (locality == null) {
			if (other.locality != null)
				return false;
		} else if (!locality.equals(other.locality))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (warehouseType != other.warehouseType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WarehouseProduct [locality=" + locality + ", quantity=" + quantity + ", warehouseType=" + warehouseType
				+ "]";
	}
}
