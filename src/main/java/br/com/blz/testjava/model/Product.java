package br.com.blz.testjava.model;

import java.util.List;

/**
 * <b>Product</b> is the entity identify by your sku <br/> 
 * and presented by your name.
 * 
 * @author Renan.Farias
 *
 */
public class Product {
	private Integer sku;
	private String name;
	private List<Warehouse> inventory;
	
	public Product(Integer sku, String name, List<Warehouse> inventory) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
	}
	
	public Integer getSku() {
		return sku;
	}
	public void setSku(Integer sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Warehouse> getInventory() {
		return inventory;
	}
	public void setInventory(List<Warehouse> inventory) {
		this.inventory = inventory;
	}
	
	
	
}
