package br.com.blz.produtos.apirest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_WAREHOUSE")
public class EntityWarehouse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String locality;
	private int quantity;
	private String type;
	
	
	public EntityWarehouse() {
		super();
	}
	
	public EntityWarehouse(String locality, int quantity, String type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	public EntityWarehouse(long id, String locality, int quantity, String type) {
		super();
		this.id = id;
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

     
}
