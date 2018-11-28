package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "inventory_tb")
public class Inventory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_inventory")
	private long id;

	@Transient
	private int quantity;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "warehouses_inventory")
	private List<Warehouse> warehouses;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	public Inventory() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {		
		quantity = 0;
		this.warehouses.forEach(wh -> {
			quantity += wh.getQuantity();
		});		
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	@JsonIgnore
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", quantity=" + quantity + ", warehouses=" + warehouses + "]";
	}

}
