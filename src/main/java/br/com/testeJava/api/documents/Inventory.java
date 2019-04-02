package br.com.testeJava.api.documents;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
"quantity",
"warehouses"
})
public class Inventory {

	@JsonProperty("quantity")
	private Integer quantity;
	@JsonProperty("warehouses")
	private List<Warehouse> warehouses = null;
		
	
	public Inventory() {
	}
	
	public Inventory(Integer quantity, List<Warehouse> warehouses) {
		super();
		this.quantity = quantity;
		this.warehouses = warehouses;
	}

		
	@JsonProperty("quantity")
	public Integer getQuantity() {
		return quantity;
	}
	
	@JsonProperty("quantity")
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("warehouses")
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	@JsonProperty("warehouses")
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@Override
	public String toString() {
		return "Inventory [quantity=" + quantity + ", warehouses=" + warehouses + "]";
	}
			
}
