package br.com.blz.testjava.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author andrey
 * @since 2019-11-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {

	@JsonProperty(required = true)
	private List<Warehouse> warehouses;

	@JsonProperty("quantity")
	public Integer getQuantity() {
		Integer quantity = 0;
		
		if (this.warehouses==null) return null;
		
		for (Warehouse warehouse : this.warehouses) {
			if (warehouse!=null && warehouse.getQuantity()!=null) 
				quantity += warehouse.getQuantity();
		}

		return quantity;
	}

	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
}
