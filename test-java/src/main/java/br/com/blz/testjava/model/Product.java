package br.com.blz.testjava.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.google.common.base.Objects;

public class Product {

	@NotNull
	private Integer sku;
	@NotNull
	private String name;
	@NotNull
	private Inventory inventory;
	@Null
	private Boolean isMarketable;
	
	Product(){
	}
	
	public Product(Integer sku, String name, Inventory inventory, Boolean isMarketable) {
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
	}

	public Integer getSku() {
		return this.sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Boolean isMarketable() {
		return this.isMarketable;
	}

	public void setMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Product)){
        	return false;
        } 

        if(obj == this){
        	return true;
        }

        return this.getSku().intValue() == ((Product) obj).getSku().intValue();
    }
    
    @Override
    public int hashCode() {
    	return Objects.hashCode(getSku());
    }

}
