package br.com.blz.testjava.model;

import static org.assertj.core.util.Preconditions.checkNotNull;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public final class Product {

	private Integer sku;
	@NotEmpty
	private String name;
	@NotNull
	private Inventory inventory;
	private boolean isMarketable;

	public Product(final Integer sku, final String name, final Inventory inventory, final boolean isMarketable) {
		this.sku = sku;
		this.name = name;
		this.inventory = checkNotNull(inventory);
		this.isMarketable = isMarketable;
	}
	
	public Product(){
		
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(final Integer sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(final Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean isMarketable() {
		return isMarketable;
	}

	@JsonProperty("isMarketable")
	public void setMarketable(final boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Product) || other == null) {
			return false;
		}

		if (other == this) {
			return true;
		}

		final Product product = (Product) other;
		return Objects.equal(product.sku, this.sku);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.sku);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("sku", sku).add("name", name)
				.add("inventory", inventory)
				.add("isMarketable", isMarketable)
				.toString();
	}
}
