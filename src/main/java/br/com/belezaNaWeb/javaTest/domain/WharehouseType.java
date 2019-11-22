package br.com.belezaNaWeb.javaTest.domain;

public enum WharehouseType {

	ECOMMERCE ("E-Commerce"), PHYSICAL_STORE("Physical Store");
	
	private String description;
	
	WharehouseType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
