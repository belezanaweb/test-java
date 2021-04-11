package br.com.blz.testjava.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Embeddable
public class Inventory {

	private Integer quantity;
	@Embedded
	@ElementCollection
	@NotNull
	private List<Warehouse> warehouses = new ArrayList<>();

	public Integer getQuantity() {
		return warehouses.stream().map(warehouse -> warehouse.getQuantity()).reduce(0, Integer::sum);
	}

}
