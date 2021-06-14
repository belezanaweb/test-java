package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.blz.testjava.enums.WarehouseTypeEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Warehouse {
	private String locality;
	private Integer quantity;
	private WarehouseTypeEnum type;

	public Warehouse() {
	}

	public Warehouse(String locality, Integer quantity, WarehouseTypeEnum type) {
		super();
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

	@Override
	public String toString() {
		return "Warehouse [locality=" + this.locality + ", quantity=" + this.quantity + ", type=" + this.type + "]";
	}
}
