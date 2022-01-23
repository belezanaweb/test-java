package br.com.blz.testjava.data.dto;

public class ProductListDTO {
	private String sku;
	private String name;
	
	public ProductListDTO() {}
	
	public ProductListDTO(String sku, String name) {
		super();
		this.sku = sku;
		this.name = name;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}
	public String getName() {
		return name;
	}
	
	
}
