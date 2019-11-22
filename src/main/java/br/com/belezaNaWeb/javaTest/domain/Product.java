package br.com.belezaNaWeb.javaTest.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class Product implements Serializable {

	@ApiModelProperty(value = "Product Identification")
	private Long sku;

	@ApiModelProperty(value = "Product Name")
	private String name;

	@ApiModelProperty(value = "Product Inventory")
	private Inventory inventory;

	@ApiModelProperty(value = "Product is Marketable")
	private boolean isMarketable;

	private static final long serialVersionUID = 1L;

	public boolean isMarketable() {
		return inventory != null && Long.valueOf(inventory.getQuantity()) > 0;
	}

	public Product() {
	}

	public Product(Long sku) {
		super();
		this.sku = sku;
	}

	public Product(Long sku, String name) {
		super();
		this.sku = sku;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + "]";
	}

}
