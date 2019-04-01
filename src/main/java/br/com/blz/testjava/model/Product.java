package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_GRAPH")
	private Long productSku;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	public Product() {}

	/**
	 * @param productSku
	 * @param name
	 */
	public Product(Long productSku, String name) {
		super();
		this.productSku = productSku;
		this.name = name;
	}

	/**
	 * @return the productSku
	 */
	public Long getProductSku() {
		return productSku;
	}

	/**
	 * @param productSku the productSku to set
	 */
	public void setProductSku(Long productSku) {
		this.productSku = productSku;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [productSku=" + productSku + ", name=" + name + "]";
	}
	
}
