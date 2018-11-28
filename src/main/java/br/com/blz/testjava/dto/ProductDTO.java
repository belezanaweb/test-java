package br.com.blz.testjava.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class ProductDTO {

	Long sku;
	
	@NotNull(message = "O nome do produto deve ser especificado")
	String name;

	@Null(message = "Nao eh possivel definir se produto deve ser comercializado ou nao")
	Boolean isMarketable;

	@NotNull(message = "As informacoes de inventario, para inclusao de dados warehouse, devem ser especificadas")
	InventoryDTO inventory;

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getIsMarketable() {
		return isMarketable;
	}
	
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	public InventoryDTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}

}
