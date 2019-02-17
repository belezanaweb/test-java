package br.com.blz.testjava.view;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.blz.testjava.model.Stock;

public class ProductStockView implements Serializable {
	
	private Integer sku;
	
	private String name;

	private Stock inventory;
	
	private Boolean isMarketable;

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

	public Stock getInventory() {
		return inventory;
	}

	public void setInventory(Stock inventory) {
		this.inventory = inventory;
	}

	public Boolean getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
}
