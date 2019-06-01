package br.com.blz.testjava.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


/**
 * Classe específica para indicar a quantidade de um determinado produto em um Warehouse.
 * Também é específica para ser usada na contagem da quantidade desse produto em estoque.
 *   A quantidade em estoque é a soma das quantidades em cada Warehouse.
 */
@Entity
@Table(name="warehouseproductcount")
public class Warehouse {


	
	@EmbeddedId
	private WarehouseId id;
	
	@Column
	private Integer quantity;
	
	//@Column
	@Enumerated(EnumType.STRING)
	private WarehouseType type;
	

	public Warehouse() {
	}


	public WarehouseId getId() {
		return id;
	}

	public void setId(WarehouseId id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public WarehouseType getType() {
		return type;
	}

	public void setType(WarehouseType type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Warehouse other = (Warehouse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	

}
