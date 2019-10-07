package br.com.blz.testjava.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "inventories")
public final class Inventory {

	@Id
	@GeneratedValue
	private Integer id;

	private int quantity;

	@NotNull(message = "Please provide a warehouse")
//	@NotEmpty(message = "Please provide a warehouse")
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

}
