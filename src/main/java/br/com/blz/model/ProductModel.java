package br.com.blz.model;

public class ProductModel {

	private Integer sku;
	
	private String name;
	
	private boolean isMarketable;

	private InventoryModel inventory;

	public ProductModel() {
		super();
	}
	
	public ProductModel(Integer sku, String name, InventoryModel inventory) {
		super();
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

	public InventoryModel getInventory() {
		return inventory;
	}

	public void setInventory(InventoryModel inventory) {
		this.inventory = inventory;
	}
	
	public boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	
	@Override
	public int hashCode() {
		return this.getSku() != null ? this.getSku().hashCode() : null;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		}
		
		ProductModel p = (ProductModel)obj;
		
		return this.getSku().equals(p.getSku());
	}
}