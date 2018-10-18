package br.com.blz.testjava.dto;

import java.io.Serializable;

import br.com.blz.testjava.dominio.Sku;

public class SkuDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long sku;
	private String name;
	private InventoryDTO inventoryDTO;
	
	public SkuDTO() {
		
	}
	
	public SkuDTO(Sku obj) {
		super();
		this.sku = obj.getSku();
		this.name = obj.getName();
		this.inventoryDTO = new InventoryDTO(obj.getInventory());
	}

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
		return inventoryDTO;
	}

	public void setInventory(InventoryDTO inventoryDTO) {
		this.inventoryDTO = inventoryDTO;
	}
	
	//adicionei o get no isMarketable apenas para o Json conter a propriedade identida do Json solicitado como resultado (isMarketable)
	//Se eu tiro o get do método "getIsMarketable", que normalmente adotamos nas convenções dos java beans como isMarketable(), a propriedade no json ficaria assim: marketable
	public Boolean getIsMarketable() {
		return this.inventoryDTO.getQuantity() > 0;
	}	
}
