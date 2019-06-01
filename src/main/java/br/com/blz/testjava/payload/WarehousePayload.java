package br.com.blz.testjava.payload;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class WarehousePayload implements Serializable {


	private static final long serialVersionUID = 9141271635268454215L;

	private String locality;

	private Integer quantity;
	
	private String type;
	

	public WarehousePayload() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
