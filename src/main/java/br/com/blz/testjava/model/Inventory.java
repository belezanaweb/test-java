package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Inventory implements Serializable {

	private static final long serialVersionUID = 4369025964227817468L;

	@Transient
	private Integer quantity;
	
	@ElementCollection
	private Set<Warehouse> warehouses = new HashSet<Warehouse>();

	public Integer getQuantity() {
		return this.getWarehouses().stream().map(Warehouse::getQuantity).reduce(0, Integer::sum).intValue();
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Set<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Set<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}	
}
