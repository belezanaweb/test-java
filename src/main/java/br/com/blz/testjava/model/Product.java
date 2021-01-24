package br.com.blz.testjava.model;


public class Product {

	private Long sku;
	private String name;
	private boolean isMarketable;
	private Inventory iventory = new Inventory();;

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public Inventory getIventory() {
		return iventory;
	}

	public void setIventory(Inventory iventory) {
		this.iventory = iventory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

}
