package br.com.blz.testjava.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class that represents the Product entity.
 * 
 * @author Andre Barroso
 *
 */
@Entity
public class Product {

	/**
	 * Product ID.
	 */
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long productId;

	/**
	 * Product SKU.
	 */
	@Column(nullable = false, unique = true)
	private Long sku;
	
	/**
	 * Product name.
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * Product inventory.
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
	@JoinColumns({@JoinColumn( name = "inventory_id", referencedColumnName = "inventory_id")})
	private Inventory inventory;

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * Gets sku
	 * @return sku
	 */
	public Long getSku() {
		return sku;
	}

	/**
	 * Sets sku
	 * @param sku Product sku
	 */
	public void setSku(Long sku) {
		this.sku = sku;
	}

	/**
	 * Gets name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name
	 * @param name Product name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets inventory
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Sets inventory
	 * @param inventory Product inventory
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Method responsible for defining whether a product is negotiable.
	 * 
	 * @return isMarketable
	 */
	public boolean isMarketable() {
		
		boolean isMarketable = false;
		
		if(this.inventory != null) {
			isMarketable = this.inventory.getQuantity() > 0L;
		}
		return isMarketable;
	}

}
