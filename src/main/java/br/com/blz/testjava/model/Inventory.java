package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Evandro Lopes da Rocha (evandro.esw@gmail.com)
 * @date 13/06/2019.
 */
@Entity
@Table(name="inventory")
public class Inventory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7089879963205928931L;
	
	private long id;
	private long quantity;
	private List<Warehouses> warehouses;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "quantity", nullable=true)
	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@OneToMany(fetch = FetchType.EAGER)
	public List<Warehouses> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouses> warehouses) {
		this.warehouses = warehouses;
	}
}
