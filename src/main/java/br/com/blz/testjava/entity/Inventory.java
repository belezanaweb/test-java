package br.com.blz.testjava.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "inventory")
	private List<InventoryWarehouse> warehouses;

	public Inventory() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<InventoryWarehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<InventoryWarehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
