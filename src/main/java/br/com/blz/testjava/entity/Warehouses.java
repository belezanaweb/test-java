package br.com.blz.testjava.entity;

/**
 * Classe referente ao objeto Warehouses
 * @author jmestre
 */
public class Warehouses {

	private String locality;
	private Integer quantity;
	private String type;
	
	public Warehouses(String locality, Integer quantity, String type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public String getLocality() {
		return locality;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public String getType() {
		return type;
	}
}
