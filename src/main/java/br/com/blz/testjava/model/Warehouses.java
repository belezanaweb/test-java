/**
 * 
 */
package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ocean
 *
 */
@Entity
@Table(name="warehouses")
public class Warehouses implements Serializable {

	private static final long serialVersionUID = -4265953307933132219L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idWarehouse")
	@JsonIgnore
	private Long idWarehouse;

	@Column(name="locality")
	private String locality;
	
	@Column(name="quantity")
	private Long quantity;
		
	@Column(name="type")
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "sku")
	@JsonBackReference
	private Product product;

	/**
	 * @return the idWarehouse
	 */
	public Long getIdWarehouse() {
		return idWarehouse;
	}

	/**
	 * @param idWarehouse the idWarehouse to set
	 */
	public void setIdWarehouse(Long idWarehouse) {
		this.idWarehouse = idWarehouse;
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
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
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

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}	
	
}