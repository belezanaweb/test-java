package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "warehouse_tb")
public class Warehouse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_warehouse")
	private long id;

	private String locality;

	private int quantity;

	@Enumerated(EnumType.STRING)
	private WarehouseType type;

	public Warehouse() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public WarehouseType getType() {
		return type;
	}

	public void setType(WarehouseType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", locality=" + locality + ", quantity=" + quantity + ", type=" + type + "]";
	}

}
