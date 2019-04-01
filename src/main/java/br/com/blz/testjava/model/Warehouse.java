package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WAREHOUSE")
public class Warehouse implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "LOCALITY", nullable= false, length= 55)
	private String locality;

	@Column(name = "QUANTITY", nullable= false, length= 9)
	private Integer quantity;

	@Id
	@Column(name = "TYPE", nullable= false, length= 20)
	private String type;

	@Column(name = "PRODUCT_SKU", nullable= false)
	private Long productSku;

	public Warehouse() {}

	/**
	 * @param locality
	 * @param quantity
	 * @param type
	 * @param productSku
	 */
	public Warehouse(String locality, 
			Integer quantity, String type, Long productSku) {
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
		this.productSku = productSku;
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

	/**
	 * @return the inventoryId
	 */
	public Long getProductSku() {
		return productSku;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setProductSku(Long productSku) {
		this.productSku = productSku;
	}

	@Override
	public String toString() {
		return "Warehouse [locality=" + locality + ", quantity=" + quantity + 
				", type=" + type + ", productSku=" + productSku +"]";
	}

}
