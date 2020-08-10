package br.com.blz.produtos.apirest.view;


import javax.validation.constraints.NotNull;

import br.com.blz.produtos.apirest.entity.EntityProduto;

public class InserirProdutoViewProdutoRequest {
	
	
	private long sku;
	@NotNull(message = "Campo nulo: name")
	private String name;
	private InserirProdutoViewInventoryRequest inventory;
	
	//-------------------------------------------
	public InserirProdutoViewProdutoRequest() {
		super();
	}

	public InserirProdutoViewProdutoRequest(long sku, String name, InserirProdutoViewInventoryRequest inventory) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
	}
	//-------------------------------------------

	public long getSku() {
		return sku;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InserirProdutoViewInventoryRequest getInventory() {
		return inventory;
	}

	public void setInventory(InserirProdutoViewInventoryRequest inventory) {
		this.inventory = inventory;
	}
	
	//-------------------------------------------
	
	public EntityProduto getEntityProduto() {
		EntityProduto entityProduto = new EntityProduto();
		
		entityProduto.setSku(this.sku);
		entityProduto.setName(this.name);
		if (this.inventory!=null) entityProduto.setWarehouses(this.inventory.getEntityWarehouses());
		
		return entityProduto;
	}
	
}
