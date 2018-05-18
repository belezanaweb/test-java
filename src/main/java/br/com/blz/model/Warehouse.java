package br.com.blz.model;

import br.com.blz.enums.WarehouseTypeEnum;

public class Warehouse {
	
	private String locality;
	private Integer quantity;
	private WarehouseTypeEnum type;
	
	public Warehouse() {
		this.quantity = 0;
	}
	
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
	public WarehouseTypeEnum getType() {
		return type;
	}
	public void setType(WarehouseTypeEnum type) {
		this.type = type;
	}
}
