package br.com.blz.testjava.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.blz.testjava.jsons.JsonRecord;

@Entity
@Component
@Table(name = "inventory")
public class Inventory implements Serializable, JsonRecord{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5991157097885855030L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Transient
	private Long quantity;
	
	@JsonProperty
	@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Warehouse> warehouses;

	public Inventory() {
	}
	
	public Inventory(Long id, Long quantity, List<Warehouse> warehouses) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.warehouses = warehouses;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuantity() {
		quantity = 0L;
		if (getWarehouses() != null && !getWarehouses().isEmpty()) {
			for (Warehouse warehouse : getWarehouses()) {
				quantity = quantity + warehouse.getQuantity();
			}
		}
		return quantity;
	}
	
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<Warehouse> warehouses) {
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (warehouses == null) {
			if (other.warehouses != null)
				return false;
		} else if (!warehouses.equals(other.warehouses))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", quantity=" + quantity + ", warehouses=" + warehouses + "]";
	}
}