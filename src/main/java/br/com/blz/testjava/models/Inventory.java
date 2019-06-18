package br.com.blz.testjava.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity	
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="INVENTORY_ID")
	Long id;	
	Integer quantity;
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
	List<Warehouse> warehouses = new ArrayList<>();
	
	
	public Inventory(List<Warehouse> warehouses) {
			this.warehouses = warehouses;
	}
	
	public Inventory() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getQuantity() {
		Integer whQuantity=0;
		for(Warehouse warehouse : warehouses) {
			whQuantity+=warehouse.quantity;
		}
		this.quantity = whQuantity;
		return quantity;
	}
		
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public Inventory(Long id, List<Warehouse> warehouses) {
		super();
		this.id = id;
        this.warehouses = warehouses;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((warehouses == null) ? 0 : warehouses.hashCode());
		return result;
	}


	
	
	
	
}
