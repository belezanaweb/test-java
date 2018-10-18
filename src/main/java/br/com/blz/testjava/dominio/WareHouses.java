package br.com.blz.testjava.dominio;

import java.io.Serializable;

import br.com.blz.testjava.dominio.enums.TypeWareHouse;

public class WareHouses implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String locality;
	private Integer quantity;	
	private TypeWareHouse type;
	
	public WareHouses() {
		
	}

	public WareHouses(String locality, Integer quantity, TypeWareHouse type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public TypeWareHouse getType() {
		return type;
	}

	public void setType(TypeWareHouse type) {
		this.type = type;
	}
	
}
