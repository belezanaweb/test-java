package br.com.blz.testjava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "warehouses")
public final class Warehouse {

	@Id
	@GeneratedValue
	private Integer id;

	@NotEmpty(message = "Please provide a locality to warehouse")
	private String locality;

	@NotNull(message = "Please provide a quantity to warehouse")
	@Min(0)
	private int quantity;

	@NotEmpty(message = "Please provide a type to warehouse")
	private String type;

	public Warehouse() {

	}

	public Warehouse(final String locality, final int quantity, final String type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public final String getLocality() {
		return locality;
	}

	public final void setLocality(final String locality) {
		this.locality = locality;
	}

	public final int getQuantity() {
		return quantity;
	}

	public final void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public final String getType() {
		return type;
	}

	public final void setType(final String type) {
		this.type = type;
	}

}
