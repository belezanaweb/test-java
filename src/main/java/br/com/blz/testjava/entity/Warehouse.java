package br.com.blz.testjava.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "locality", "type" }) )
@Entity
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "warehouse")
	private List<InventoryWarehouse> warehouses;

	private String locality;

	@Enumerated(EnumType.STRING)
	private Type type;

	public Warehouse() {

	}

	public Warehouse(String locality, Type type) {
		this.locality = locality;
		this.type = type;
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

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
