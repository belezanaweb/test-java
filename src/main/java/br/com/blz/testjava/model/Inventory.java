package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Inventory implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@Transient
	private int quantity;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Warehouse> warehouses;

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity() {
		int sum = 0;
		List<Warehouse> warehouses = this.getWarehouses();
		
		for (Warehouse warehouse : warehouses) {
			sum = sum + warehouse.getQuantity();
		}
		
		this.quantity = sum;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public int getId() {
		return id;
	}

}
