package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "product_tb")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private long id;

	private long sku;

	private String name;

	private boolean isMarketable;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private Inventory inventory;

	public Product() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public boolean isMarketable() {
		return (this.inventory.getQuantity() > 0 ? true : false);
	}

	public void setIsMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", sku=" + sku + ", name=" + name + ", isMarketable=" + isMarketable
				+ ", inventory=" + inventory + "]";
	}

}
