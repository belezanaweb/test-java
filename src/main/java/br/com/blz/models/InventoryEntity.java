package br.com.blz.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "iventory")
public class InventoryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Transient
	private int quantity;
	
	
	@OneToMany(cascade = CascadeType.ALL)	
	private List<WarehouseEntity> warehouses = new ArrayList<WarehouseEntity>();


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getQuantity() {
		this.quantity = 0;
		for (WarehouseEntity warehouseEntity : warehouses) {
			this.quantity = quantity + warehouseEntity.getQuantity();
		}
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public List<WarehouseEntity> getWarehouses() {
		return warehouses;
	}


	public void setWarehouses(List<WarehouseEntity> warehouses) {
		this.warehouses = warehouses;
	}

	


	

	



	
	
	

}
