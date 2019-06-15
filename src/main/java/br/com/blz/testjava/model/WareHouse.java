package br.com.blz.testjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class that represents the WareHouse entity.
 * 
 * @author Andre Barroso
 *
 */
@Entity
public class WareHouse {

	/**
	 * Inventory ID.
	 */
	@Id
	@Column(name = "wareHouse_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long wareHouseId;

	/**
	 * The WareHouse locality.
	 */
	@Column(nullable = false)
	private String locality;

	/**
	 * The WareHouse quantity.
	 */
	@Column(nullable = false)
	private Long quantity;
	
	/**
	 * The WareHouse enum type.
	 */
	@Column(nullable = false)
	private WareHouseTypeEnum type;
	
	/**
	 * @return the wareHouseId
	 */
	public Long getWareHouseId() {
		return wareHouseId;
	}

	/**
	 * @param wareHouseId the wareHouseId to set
	 */
	public void setWareHouseId(Long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}
	
	/**
	 * Gets locality
	 * @return locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * Sets locality
	 * @param locality WareHouse locality
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * Gets quantity
	 * @return WareHouse quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * Sets quantity
	 * 
	 * @param quantity WareHouse quantity
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets type
	 * @return type
	 */
	public WareHouseTypeEnum getType() {
		return type;
	}

	/**
	 * Sets type
	 * @param type WareHouse type
	 */
	public void setType(WareHouseTypeEnum type) {
		this.type = type;
	}
	
}
