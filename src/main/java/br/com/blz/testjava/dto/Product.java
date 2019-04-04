package br.com.blz.testjava.dto;

public class Product {

	private Long sku;
	private String name;
	private Inventory inventory;
	private boolean markatable;

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

	public boolean isMarkatable() {
		return markatable;
	}

	public void setMarkatable() {
		markatable = this.inventory.getQuantity() > 0;
	}

	@Override
    public boolean equals(Object o) { 
        if (o == this) { 
            return true; 
        }
        if (!(o instanceof Product)) { 
            return false; 
        }
        Product c = (Product) o;        
        return Double.compare(sku, c.sku) == 0;
	}
}
