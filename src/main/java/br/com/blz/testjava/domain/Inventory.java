package br.com.blz.testjava.domain;

import static java.util.Optional.ofNullable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {

	@JsonProperty(required = true)
	private List<Warehouse> warehouses;

	@JsonProperty
	public Integer getQuantity() {
		Integer quantity = 0;

		for (Warehouse warehouse : this.getWarehouses()) {

			quantity += ofNullable(warehouse).map(Warehouse::getQuantity).orElse(0);
		}

		if (quantity != 0) {
			return quantity;
		} else {
			return null;
		}
	}

	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
}