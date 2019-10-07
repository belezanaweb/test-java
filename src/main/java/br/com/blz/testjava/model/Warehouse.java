package br.com.blz.testjava.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "warehouses")
public final class Warehouse {

	@Id
	@GeneratedValue
	private Integer id;

//	@NotEmpty(message = "Please provide a locality to warehouse")
	private String locality;

	@NotNull(message = "Please provide a quantity to warehouse")
	@Min(0)
	private int quantity;

//	@NotEmpty(message = "Please provide a type to warehouse")
	private String type;

	public Warehouse() {

	}

	public Warehouse(final String locality, final int quantity, final String type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}
}
