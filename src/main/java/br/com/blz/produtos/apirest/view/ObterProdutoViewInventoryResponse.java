package br.com.blz.produtos.apirest.view;

import java.util.ArrayList;
import java.util.List;


import br.com.blz.produtos.apirest.entity.EntityWarehouse;

public class ObterProdutoViewInventoryResponse {
	
	
	private int quantity;
	private List<ObterProdutoViewWarehouseResponse> warehouses = new ArrayList<ObterProdutoViewWarehouseResponse>();
	
	//-------------------------------------------	
	public ObterProdutoViewInventoryResponse() {
		super();
	}
	
	public ObterProdutoViewInventoryResponse(int quantity, List<ObterProdutoViewWarehouseResponse> warehouses) {
		super();
		this.quantity = quantity;
		this.warehouses = warehouses;
	}

	public ObterProdutoViewInventoryResponse(List<EntityWarehouse> warehouses) {
		super();
		for (EntityWarehouse item : warehouses) {
			this.quantity+= item.getQuantity();
			this.warehouses.add(new ObterProdutoViewWarehouseResponse(item));
        }
		
	}
	//-------------------------------------------

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public List<ObterProdutoViewWarehouseResponse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<ObterProdutoViewWarehouseResponse> warehouses) {
		this.warehouses = warehouses;
	}
	//-------------------------------------------
	
}


