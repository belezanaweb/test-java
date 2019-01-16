package br.com.blz.testjava.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.blz.testjava.model.Inventory;

public class Sku implements Serializable {

	private static final long serialVersionUID = 2019010901L;
	
	public Sku() {
		super();
	}
	
	public Sku(Long id, String name, Inventory inventory) {
		super();
		this.id = id;
		this.name = name;
		this.inventory = inventory;
	}

	@Id
	@JsonProperty(value = "sku", required=true)
	private Long id;

	@JsonProperty(value = "name", required=true)
	private String name;

	@JsonProperty(value = "inventory", required=true)
	private Inventory inventory;
	
	private Boolean isMarketeable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	@Transactional
	@JsonProperty(value = "isMarketeable", required=false)
	public Boolean getIsMarketable() {
		if(isMarketeable == null) {
			isMarketeable = inventory.getQuantity() >= 1;
		}
		
		return isMarketeable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sku other = (Sku) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sku [id=" + id + ", name=" + name + ", inventory=" + inventory + ", getIsMarketable()="
				+ getIsMarketable() + "]";
	}
}
