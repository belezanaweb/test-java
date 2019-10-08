package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public final class Product {

	@Id
	@NotNull
	private int sku;

	@NotEmpty
	private String name;

	@JsonProperty("isMarketable")
	private boolean marketable;

	@NotNull
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Inventory inventory;
}
