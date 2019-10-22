package br.com.blz.testjava.domain;

import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	@JsonProperty(required = true)
	private Integer sku;

	@JsonProperty(required = true)
	private String name;

	@JsonProperty(required = true)
	private Inventory inventory;

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@JsonProperty("isMarketable")
	public Boolean isMarketable() {
		return ofNullable(this.inventory).map(Inventory::getQuantity).map(quantity -> quantity != null)
				.orElse(Boolean.FALSE);
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", inventory=" + inventory + "]";
	}
}
