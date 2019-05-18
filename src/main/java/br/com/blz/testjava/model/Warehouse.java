package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Warehouse implements Serializable {

	private static final long serialVersionUID = -4863995545961591229L;

	private String locality;
	
	private Integer quantity;
	
	private String type;

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
