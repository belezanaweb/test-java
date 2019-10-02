package br.com.blz.testjava.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "products")
public final class Product {

	@Id
	@NotNull(message = "Please provide a sku")
	@Min(value = 1, message = "Please provide sku greater then 1")
	private int sku;

	@NotEmpty(message = "Please provide a name")
	private String name;

	@JsonProperty("isMarketable")
	private boolean marketable;

	@NotNull(message = "Please provide a inventory")
	@Valid
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Inventory inventory;

	public Product() {

	}

	public Product(final int sku, final String name, final boolean marketable, final Inventory inventory) {
		this.sku = sku;
		this.name = name;
		this.marketable = marketable;
		this.inventory = inventory;
	}

	public final int getSku() {
		return sku;
	}

	public final void setSku(final int sku) {
		this.sku = sku;
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final boolean isMarketable() {
		return marketable;
	}

	public final void setMarketable(boolean marketable) {
		this.marketable = marketable;
	}

	public final Inventory getInventory() {
		return inventory;
	}

	public final void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
