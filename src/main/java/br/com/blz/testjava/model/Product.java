package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "products")
public final class Product {

	@Id
	@NotNull(message = "Please provide a sku")
	@Min(value = 1, message = "Please provide sku greater then 1")
	private int sku;

//	@NotEmpty(message = "Please provide a name")
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
}
