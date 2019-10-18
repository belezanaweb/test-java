package br.com.blz.testjava.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	@NotNull
	private Long sku;
	
	private String name;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_inventory")
	private Inventory inventory;
	
	@Transient
	private boolean isMarketable;

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

	public boolean isMarketable() {
		if(inventory !=null && inventory.getWarehouses().size() > 0) {
			return this.isMarketable = true;	
		}
		return this.isMarketable = false;
	}

	
	
}