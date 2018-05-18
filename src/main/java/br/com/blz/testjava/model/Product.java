package br.com.blz.testjava.model;

import static com.google.common.base.Objects.equal;

import com.google.common.base.Objects;

public class Product {

	private Integer sku;
	private String name;
	private Inventory inventory;
	private boolean isMarkeable;

	protected Product() {
	}

	public Integer getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public boolean isMarkeable() {
		return isMarkeable;
	}

	public void calculeMarkeable() {
		this.isMarkeable = inventory.getQuantity() > 0;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Product)) {
			return false;
		}

		final Product anotherProduct = (Product) obj;
		return equal(this.sku, anotherProduct.sku);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(sku);
	}
}