package br.com.blz.testjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;

public final class Warehouse {
	@NotEmpty
	private String locality;
	private Integer quantity;
	@NotEmpty
	private String type;

	public Warehouse(final String locality, final Integer quantity, final String type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}
	
	public Warehouse() {
	}
	

	public String getLocality() {
		return locality;
	}

	public void setLocality(final String locality) {
		this.locality = locality;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("locality", locality)
				.add("quantity", quantity)
				.add("type", type)
				.toString();
	}
}
