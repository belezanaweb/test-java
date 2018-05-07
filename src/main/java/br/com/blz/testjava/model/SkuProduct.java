package br.com.blz.testjava.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table
@Entity
public class SkuProduct {
	
	private long sku;
	private String name;
	private Inventory inventory;
	private boolean isMarketable;
	@Transient
	@OneToMany
	private Warehouse warehouse;
	
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public SkuProduct() {
	
	}
	@Id
	public long getSku() {
		return sku;
	}
	public void setSku(long id) {
		this.sku = id;
	}
	public boolean isMarketable() {
		return isMarketable;
	}
	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
	
	
}
