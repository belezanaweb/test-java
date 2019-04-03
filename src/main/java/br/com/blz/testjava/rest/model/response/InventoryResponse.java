package br.com.blz.testjava.rest.model.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.blz.testjava.business.model.InventoryBO;
import br.com.blz.testjava.business.model.WharehouseBO;

@JsonPropertyOrder({ "quantity", "wharehouses"})
public class InventoryResponse {

	private InventoryBO inventory;

	public InventoryResponse(InventoryBO inventory) {
		this.inventory = inventory;
	}
	
	@JsonInclude(Include.NON_NULL)
	public Integer getQuantity() {
		return hasInventory() ? this.inventory.getQuantity() : null;
	}
	
	@JsonInclude(Include.NON_NULL)
	public List<WharehouseResponse> getWarehouses() {
		List<? extends WharehouseBO> wharehouses = this.inventory.getWarehouses();
		if(hasInventory() && wharehouses != null) {
			return this.inventory.getWarehouses().stream().map(wr -> {
				return new WharehouseResponse(wr);
			}).collect(Collectors.toList());
		}
		return null;
	}
	
	private boolean hasInventory() {
		return this.inventory != null;
	}

}
