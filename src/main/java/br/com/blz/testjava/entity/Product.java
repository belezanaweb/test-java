package br.com.blz.testjava.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	@NotNull(message = "sku is required")
	private Long sku;

	@NotNull(message = "name is required")
	private String name;

	@Valid
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "inventory")
	private Inventory inventory;

	private Boolean isMarketable;

	public Long getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Boolean getIsMarketable() {
		return this.getInventory().getQuantity() > 0;
	}

}
