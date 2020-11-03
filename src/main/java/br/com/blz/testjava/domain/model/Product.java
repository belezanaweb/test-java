package br.com.blz.testjava.domain.model;

import static br.com.blz.testjava.domain.factory.InventoryFactory.zeroQuantityInventory;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 6923768843577244901L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sku;
    
	@NotBlank(message = "products-1")
	private String name;
    
	@NotNull(message = "products-2")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
    private Inventory inventory;
    
	public Boolean isMarketable() {
		return Optional.ofNullable(this.inventory)
				.orElse(zeroQuantityInventory())
				.getQuantity() > 0;
	}
}
