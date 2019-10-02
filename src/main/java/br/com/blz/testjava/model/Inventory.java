package br.com.blz.testjava.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "inventories")
public final class Inventory {

	@Id
	@GeneratedValue
	private Integer id;

	private int quantity;

	@NotNull(message = "Please provide a warehouse")
	@NotEmpty(message = "Please provide a warehouse")
	@Valid
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Warehouse> warehouses;

	public Inventory() {

	}

	public Inventory(final int quantity, final List<Warehouse> warehouses) {
		this.quantity = quantity;
		this.warehouses = warehouses;
	}

	public final int getQuantity() {
		return quantity;
	}

	public final void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public final List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public final void setWarehouses(final List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
