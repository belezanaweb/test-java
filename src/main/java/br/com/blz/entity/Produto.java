package br.com.blz.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity(name="Produto")
public class Produto {
	
	
	private Integer sku;
	private String name;
	private boolean isMarketable;
	private Inventario inventory;
	
	public Produto() {
		
	}
	
	public Produto(Integer sku, String name, boolean isMarketable, Inventario inventory) {
		super();
		this.sku = sku;
		this.name = name;
		this.isMarketable = isMarketable;
		this.inventory = inventory;
		
	}
	

	@Id
	@Column(name = "SKU", nullable = false, precision = 2)
	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}
	
	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ISMARKETABLE", precision = 1, scale = 0)
	public boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade={ CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "ID_INVENTORY")
	public Inventario getInventory() {
		return inventory;
	}

	public void setInventory(Inventario inventory) {
		this.inventory = inventory;
	}
	
	

}
