package br.com.blz.api.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sku",length = 50, unique = true, nullable = false)
	private long sku;
	
	@NotNull
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	private InventoryEntity inventory;
	
	@Transient
	private boolean isMarketable;

	public long getSku() {
		return sku;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InventoryEntity getInventory() {
		return inventory;
	}

	public void setInventory(InventoryEntity inventory) {
		this.inventory = inventory;
	}

	public boolean isMarketable() {
		return this.getInventory().getQuantity() > 0 ? true : false;
	}


}
