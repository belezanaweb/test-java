package br.com.blz.testjava.model;

import java.util.Optional;

public class Product {

	private Long sku;
	private String name;
	private Invetory inventory;
	private boolean isMarketable;

	public Product() {}

	public Product(Long sku, String name) {
		this.sku = sku;
		this.name = name;
	}

	
	public Product(Long sku, String name, Invetory invetory) {
		this(sku,name);
		this.inventory = invetory;
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

	public Invetory getInventory() {
		return inventory;
	}

	public void setInventory(Invetory inventory) {
		this.inventory = inventory;
	}

	public boolean isMarketable() {
		return Optional.ofNullable(this.inventory)
		.orElse(new Invetory())
		.getQuantity() > 0;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

}
