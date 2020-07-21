package br.com.blz.testjava.product.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(unique = true)
	@NotNull
	private Long sku;

	@Column
	@NotBlank
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	private Inventory inventory;

	@JsonProperty("isMarketable")
	public boolean isMarketable() {
		if (this.inventory == null)
			return false;

		return this.inventory.getWarehouses().stream().map(Warehouse::getQuantity).reduce(0L, Long::sum) > 0L;
	}

}
