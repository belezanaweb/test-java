package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer sku;
	
	private String name;

	@OneToOne(cascade = {CascadeType.ALL})
	private Stock inventory;
	
	@Transient
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
		
		return (this.getInventory().getQuantity() > 0) ? true : false;
	}
}
