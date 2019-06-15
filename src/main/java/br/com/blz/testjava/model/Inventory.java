package br.com.blz.testjava.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Class that represents the product Inventory entity.
 * 
 * @author Andre Barroso
 *
 */
@Entity
public class Inventory {

	/**
	 * Inventory ID.
	 */
	@Id
	@Column(name = "inventory_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long inventoryId;

	/**
	 * Ware houses list.
	 */
	@JsonAlias({"warehouses", "wareHouses"})
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "inventory_id")
	private List<WareHouse> wareHouses;

	/**
	 * @return the inventoryId
	 */
	public Long getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}
	
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
