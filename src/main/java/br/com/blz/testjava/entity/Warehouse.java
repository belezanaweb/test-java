package br.com.blz.testjava.entity;

import java.io.Serializable;

import br.com.blz.testjava.enumeration.WarehouseTypeEnum;

//Anotacoes de persistencia nao incluidas
public class Warehouse implements Serializable {

	private static final long serialVersionUID = 4666229601027503112L;
	
	String locality;
	Integer quantity;
	WarehouseTypeEnum type;

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

	public WarehouseTypeEnum getType() {
		return type;
	}

	public void setType(WarehouseTypeEnum type) {
		this.type = type;
	}

	
}
