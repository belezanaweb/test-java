package br.com.blz.testjava.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

	
	@Id
	@EqualsAndHashCode.Include
	private Long sku;
	private String name;
	@Embedded
	private Inventory inventory;
	private Boolean isMarketable;
	
	public Boolean getIsMarketable() {
		return inventory.getQuantity().compareTo(0) > 0;
	}
	
}
