package br.com.blz.testjava.models;

import java.io.Serializable;

public class WareHouse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WareHouse() {
		// TODO Auto-generated constructor stub
	}
	
	public WareHouse(String locality,long quantity, String type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}
	
	private String locality;
	private long quantity;
	private String type;

	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
