package br.com.blz.testjava.entity;

/**
 * Classe referente ao objeto Product
 * @author jmestre
 */
public class Product {
	
	private Integer sku;
	private String name;
	private Inventory inventory;
	private boolean isMarketable;
	
	public Product(Integer sku, String name, Inventory inventory) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
	}

	public Integer getSku() {
		return sku;
	}
	
	public String getName() {
		return name;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public boolean getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
}
