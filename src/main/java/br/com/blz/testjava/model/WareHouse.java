package br.com.blz.testjava.model;

import br.com.blz.testjava.type.WareHouseType;

public class WareHouse {

	private String locality;
	private Integer quantity;
	private WareHouseType type;

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

	public WareHouseType getType() {
		return type;
	}

	public void setType(WareHouseType type) {
		this.type = type;
	}

}
