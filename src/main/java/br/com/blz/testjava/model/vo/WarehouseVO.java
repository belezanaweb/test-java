package br.com.blz.testjava.model.vo;

import java.io.Serializable;

public class WarehouseVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String locality;
	private Integer quantity;
	private String type;
	
	/**
	 * 
	 */
	public WarehouseVO() {}
	
	/**
	 * @param locality
	 * @param quantity
	 * @param type
	 */
	public WarehouseVO(String locality, Integer quantity, String type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}
	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
