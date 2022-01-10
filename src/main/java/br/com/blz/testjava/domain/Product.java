package br.com.blz.testjava.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Product implements Serializable{
	private static final long serialVersionUID = -5310882316675847665L;
	
	private Integer sku;
	private String name;
	private Inventory inventory;
	private boolean marketable;

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

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean isMarketable() {
		return marketable;
	}

	public void setMarketable(boolean marketable) {
		this.marketable = marketable;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.sku.equals(((Product)obj).getSku());
	}

}
