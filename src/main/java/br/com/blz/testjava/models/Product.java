package br.com.blz.testjava.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long sku;
	String name;
	@OneToOne(  cascade = CascadeType.ALL, orphanRemoval = true)
	Inventory inventory;
	boolean isMarketable;
	
	public Product(){
		
	}
	
	public Product( String name, Inventory inventory) {
		super();
		
		this.name = name;
		this.inventory = inventory;	
	}
	
	public Product(Long sku, String name, Inventory inventory) {
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
	public boolean isMarketable() {
		if(this.inventory!=null && this.inventory.quantity > 0) {
			return true;
			}else {
				return false;
			}		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + (isMarketable ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}
	
	


	
	
	
}
