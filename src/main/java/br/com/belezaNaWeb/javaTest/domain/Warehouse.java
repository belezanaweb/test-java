package br.com.belezaNaWeb.javaTest.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class Warehouse implements Serializable {

	@ApiModelProperty(value = "Product WhareHouse Locality")
	private String locality;

	@ApiModelProperty(value = "Product WhareHouse Quantity")
	private int quantity;

	@ApiModelProperty(value = "Product WhareHouse Type")
	private String type;

	private static final long serialVersionUID = 1L;

	public Warehouse() {
	}

	public Warehouse(String locality, int quantity, String type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locality == null) ? 0 : locality.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Warehouse other = (Warehouse) obj;
		if (locality == null) {
			if (other.locality != null)
				return false;
		} else if (!locality.equals(other.locality))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wharehouse [locality=" + locality + ", quantity=" + quantity + ", type=" + type + "]";
	}

}
