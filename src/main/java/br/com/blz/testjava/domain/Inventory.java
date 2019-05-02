package br.com.blz.testjava.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Inventory implements Serializable {

	private static final long serialVersionUID = -8294712827977853256L;
	
	private long quantity;
	private List<Warehouse> warehouses;
	
	public Inventory() {}
	
	public long getQuantity() {
		return warehouses.stream().map(Warehouse::getQuantity).reduce(0L, Long::sum).longValue();
	}
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@Override
	public String toString() {
		return "Inventory [quantity=" + quantity + ", warehouses=" + warehouses + "]";
	}
}
