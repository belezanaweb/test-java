package br.com.blz.testjava.dominio;

import java.io.Serializable;

public class Sku implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long sku;
	private String name;
	private Inventory inventory;
	
	public Sku() {
		
	}
	
	public Sku(Long sku, String name, Inventory inventory) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
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
		Sku other = (Sku) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
	
}
