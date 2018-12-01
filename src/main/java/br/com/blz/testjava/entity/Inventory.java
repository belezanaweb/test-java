package br.com.blz.testjava.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Inventory implements Serializable {

	private static final long serialVersionUID = -1523066824417023869L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private long id;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Warehouse> warehouses;

	private Integer quantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public Integer getQuantity() {
		return this.getWarehouses().stream().map(Warehouse::getQuantity).reduce(Integer::sum).get();
	}

}
