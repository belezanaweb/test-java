package br.com.blz.testjava.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class InventoryDTO {

	@Null(message = "Quantidade nao deve ser fornecida")
	Integer quantity;
	
	@NotNull(message = "No minimo um warehourse deve ser fornecido")
	List<WarehouseDTO> warehouses;

	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<WarehouseDTO> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseDTO> warehouses) {
		this.warehouses = warehouses;
	}

}
