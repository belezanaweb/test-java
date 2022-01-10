package br.com.blz.testjava.domain;

public class Warehouse {
	private String locality;
	private Integer quantity;
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

}
