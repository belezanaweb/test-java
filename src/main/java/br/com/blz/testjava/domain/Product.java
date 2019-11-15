package br.com.blz.testjava.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@Entity
public class Product {

	@Id
	private Long sku;

	@NotNull(message = "name is required")
	@NotEmpty(message = "name can't be empty")
	private String name;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "inventory")
	private Inventory inventory;

	@JsonSerialize
	@Transient
	private Boolean isMarketable() {
		return this.getInventory().quantity() > 0;
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

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", inventory=" + inventory + "]";
	}

}
