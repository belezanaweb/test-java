package br.com.blz.testjava.domain;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 5911664444791658861L;
	
	private Integer sku;
	private String name;
	private Inventory inventory;
	
	public Product() { }
	
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
	public boolean isMarketable() {
		return this.inventory.getQuantity() > 0 ? true : false;
	}
	public Integer getSku() {
		return sku;
	}
	public void setSku(Integer sku) {
		this.sku = sku;
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

	@Override
	public String toString() {
		return "Sku [sku=" + sku + ", name=" + name + ", inventory=" + inventory + ", isMarketable=" + isMarketable()
				+ "]";
	}
}
