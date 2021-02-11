package br.com.blz.testjava.data;

public class Product {

	Long sku;
	String name;
	Inventory inventory;
	
	
	
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



	public Boolean isIsMarketable() {
		
		return inventory.getQuantity() > 0;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
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
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
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
		return "Product [sku=" + sku + ", name=" + name + ", inventory=" + inventory + "]";
	}
	
	
	
}
