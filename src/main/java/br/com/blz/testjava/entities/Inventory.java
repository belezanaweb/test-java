package br.com.blz.testjava.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table
public class Inventory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Warehouse> warehouses;
	
	@Transient
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public Integer getQuantity() {
		if(quantity == null) {
			quantity = warehouses
					.stream()
					.mapToInt(Warehouse::getQuantity)
					.sum();
		}
		
		return quantity;
	}
	
}
