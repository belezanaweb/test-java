package br.com.blz.produtos.apirest.view;

import br.com.blz.produtos.apirest.entity.EntityWarehouse;


public class ObterProdutoViewWarehouseResponse {

	
	private String locality;
	private int quantity;
	private String type;
	
	
	//-------------------------------------------
	public ObterProdutoViewWarehouseResponse() {
		super();
	}	
	
	
	public ObterProdutoViewWarehouseResponse(String locality, int quantity, String type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public ObterProdutoViewWarehouseResponse(EntityWarehouse warehouse) {
		super();
		this.locality = warehouse.getLocality();
		this.quantity = warehouse.getQuantity();
		this.type     = warehouse.getType();
	}
	//-------------------------------------------

	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	//-------------------------------------------
	
}
