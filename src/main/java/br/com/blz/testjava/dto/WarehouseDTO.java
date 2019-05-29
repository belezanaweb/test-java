package br.com.blz.testjava.dto;

public class WarehouseDTO {
	private String locality;
	private Long quantity;
	private String type;

	/**
	* No args constructor for use in serialization
	* 
	*/
	public WarehouseDTO() {
	}

	/**
	* 
	* @param locality
	* @param quantity
	* @param type
	*/
	public WarehouseDTO(String locality, Long quantity, String type) {
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
