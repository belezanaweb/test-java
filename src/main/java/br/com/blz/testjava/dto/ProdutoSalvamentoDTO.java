package br.com.blz.testjava.dto;

import java.io.Serializable;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ProdutoSalvamentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@NonNull
	private Long sku;
	@NonNull
	private String name;
	@NonNull
	private InventoryDTO inventory;
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
	public InventoryDTO getInventory() {
		return inventory;
	}
	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}
	
	
	
	
	

}
