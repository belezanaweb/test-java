package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable {

	private static final long serialVersionUID = -36176769696497516L;

	private Long quantity;
	
	private List<Wharehouse> whareHouses;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public List<Wharehouse> getWhareHouses() {
		return this.whareHouses;
	}

	public void setWhareHouses(List<Wharehouse> whareHouses) {
		this.whareHouses = whareHouses;
	}
	
}
