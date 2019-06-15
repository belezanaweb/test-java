package br.com.blz.testjava.model;

/**
 * Class that represents the WareHouse entity.
 * 
 * @author Andre Barroso
 *
 */
public class WareHouse {

	/**
	 * The WareHouse locality.
	 */
	private String locality;

	/**
	 * The WareHouse quantity.
	 */
	private Long quantity;
	
	/**
	 * The WareHouse enum type.
	 */
	private WareHouseTypeEnum type;

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
