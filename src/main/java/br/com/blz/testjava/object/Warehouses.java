package br.com.blz.testjava.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Warehouses {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String locality;
	private int quantity;
	private String type;

	@JsonIgnore
	private Long productSku;

	public Warehouses(String locality, int quantity, String type) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public Warehouses() {
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getProductSku() {
		return productSku;
	}

	public void setProductSku(Long productSku) {
		this.productSku = productSku;
	}

}