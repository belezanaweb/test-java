package br.com.blz.testjava.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.blz.testjava.model.Warehouse;

public class WarehouseDTO {
	@NotNull @Size(min =1)
	private String locality;
	@NotNull
	private Integer quantity;
	@NotNull @Size(min = 1)
	private String type;
	
	public WarehouseDTO() {
		
	}
	
	public WarehouseDTO(Warehouse warehouse) {
		this.locality = warehouse.getLocality();
		this.quantity = warehouse.getQuantity();
		this.type = warehouse.getType().toString();
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
