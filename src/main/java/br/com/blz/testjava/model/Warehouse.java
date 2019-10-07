package br.com.blz.testjava.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 6226219528099147890L;
	private String locality;
	private Long quantity;
	private WarehouseType type;
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public WarehouseType getType() {
		return type;
	}
	public void setType(WarehouseType type) {
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
		if (type != other.type)
			return false;
		return true;
	}
	public static Warehouse build() {
		return new Warehouse();
	}
	public Warehouse withLocality(String locality) {
		this.locality = locality;
		return this;
	}
	public Warehouse withQuantity(Long quantity) {
		this.quantity = quantity;
		return this;
	}
	public Warehouse withType(WarehouseType type) {
		this.type = type;
		return this;
	}
}
