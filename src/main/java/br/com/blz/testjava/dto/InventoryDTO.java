package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Component
public class InventoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigDecimal quantity;
	@JsonDeserialize
	@NonNull
	private List<WareHouseDTO> warehouses;
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public List<WareHouseDTO> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<WareHouseDTO> warehouses) {
		this.warehouses = warehouses;
	}
	
	
	

}
