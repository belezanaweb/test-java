package br.com.blz.testjava.rest.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.blz.testjava.business.model.ProductInventoryBO;

@JsonPropertyOrder({ "sku", "name", "inventory"})
public class ProductInventoryResponse {
	
	private ProductInventoryBO product;
	
	private InventoryResponse inventoryResponse;

	public ProductInventoryResponse(ProductInventoryBO product) {
		super();
		this.product = product;
		
		this.inventoryResponse = this.product.getInventory() != null 
				? new InventoryResponse(this.product.getInventory())
				: null;
		
	}
	
	public Long getSku() {
		return this.product.getSku();
	}
	
	public String getName() {
		return this.product.getName();
	}
	
	@JsonInclude(Include.NON_EMPTY)
	public InventoryResponse getInventory() {
		return this.inventoryResponse;
	}
	
	@JsonProperty("isMarketable")
	public boolean isMarketable() {
		return this.inventoryResponse != null && this.inventoryResponse.getQuantity() > 0;
	}
	

}
