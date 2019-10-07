package br.com.blz.testjava.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
	private static final long serialVersionUID = -630352705024937605L;
	private Long sku;
	private String name;
	private Inventory inventory;
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
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
	@JsonProperty(value = "isMarketable")
	public Boolean isMarketable() {
		return !Long.valueOf(0l).equals(inventory.getQuantity());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
	public static Product build() {
		return new Product();
	}
	public Product withSku(Long sku) {
		this.sku = sku;
		return this;
	}
	public Product withName(String name) {
		this.name = name;
		return this;
	}
	public Product withInventory(Inventory inventory) {
		this.inventory = inventory;
		return this;
	}
	
}
