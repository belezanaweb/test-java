package br.com.blz.produtos.apirest.view;

import java.util.ArrayList;
import java.util.List;


import javax.validation.constraints.NotNull;

import br.com.blz.produtos.apirest.entity.EntityWarehouse;

public class InserirProdutoViewInventoryRequest {
	
	@NotNull(message = "Campo obritório em nulo: warehouses")
	private List<InserirProdutoViewWarehouseResponse> warehouses = new ArrayList<InserirProdutoViewWarehouseResponse>();

	
	//-------------------------------------------
	public InserirProdutoViewInventoryRequest() {
		super();
	}

	public InserirProdutoViewInventoryRequest(List<InserirProdutoViewWarehouseResponse> warehouses) {
		super();
		this.warehouses = warehouses;
	}
	//-------------------------------------------

	public List<InserirProdutoViewWarehouseResponse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<InserirProdutoViewWarehouseResponse> warehouses) {
		this.warehouses = warehouses;
	}
	//-------------------------------------------
	
	public List<EntityWarehouse> getEntityWarehouses(){
		List<EntityWarehouse> entityWarehouses = new ArrayList<EntityWarehouse>();
		
		for (InserirProdutoViewWarehouseResponse item : this.warehouses) {
			entityWarehouses.add(new EntityWarehouse(item.getLocality(), item.getQuantity(), item.getType()));
		}
		
		return entityWarehouses;
	}
	
}
