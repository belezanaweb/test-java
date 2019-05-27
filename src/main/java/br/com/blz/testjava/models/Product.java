package br.com.blz.testjava.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.blz.testjava.jsons.JsonChildSerializer;
import br.com.blz.testjava.jsons.JsonRecord;

@Entity
@Component
@Table(name = "product")
public class Product implements Serializable, JsonRecord {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3254961752075542292L;
	
	@Id
	private Long sku;
	
	@Column(name = "name", nullable = false)
	private String name;

	@JsonSerialize(using = JsonChildSerializer.class)
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
	private Inventory inventory;
	
	@Transient
	private Boolean isMarketable;
	
	public Product() {
	}
	
	public Product(Long sku, String name, Inventory inventory, Boolean isMarketable) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
	}
	
	
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	@Override
	public Long getId() {
		return getSku();
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
	public Boolean getIsMarketable() {
		isMarketable = Boolean.FALSE;
		if (getInventory() != null && getInventory().getQuantity() != null && getInventory().getQuantity() > 0) {
			isMarketable = Boolean.TRUE;
		}
		return isMarketable;
	}
	
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Product other = (Product) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", inventory=" + inventory + ", isMarketable=" + isMarketable
				+ "]";
	}
}