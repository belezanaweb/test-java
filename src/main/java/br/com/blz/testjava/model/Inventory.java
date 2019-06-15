package br.com.blz.testjava.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * 
 * Class that represents the product Inventory entity.
 * 
 * @author Andre Barroso
 *
 */
public class Inventory {

	/**
	 * Ware houses list.
	 */
	@JsonAlias({"warehouses", "wareHouses"})
	private List<WareHouse> wareHouses;

	/**
	 * Method responsible for recovering the amount of inventory.
	 * 
	 * @return Quantity.
	 */
	public Long getQuantity() {
		
		Long quantity = 0L;
		
		if(this.wareHouses != null && !this.wareHouses.isEmpty()) {
			quantity = this.wareHouses.stream().mapToLong(w -> w.getQuantity()).sum();
		}
		return quantity;
	}

	/**
	 * Gets wareHouses
	 * @return wareHouses
	 */
	public List<WareHouse> getWareHouses() {
		return wareHouses;
	}

	/**
	 * Sets wareHouses
	 * @param Inventory wareHouses
	 */
	public void setWareHouses(List<WareHouse> wareHouses) {
		this.wareHouses = wareHouses;
	}
	
}
