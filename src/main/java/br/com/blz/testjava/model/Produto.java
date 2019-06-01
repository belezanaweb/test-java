package br.com.blz.testjava.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * CUIDADO!!!   getters e setters não são padrão, não foram gerado automáticamente. 
 *
 *
 */
@Entity
@Table(name="produto")
public class Produto {
	

	@Id
	@Column
	private Integer sku;	

	@Column
	private String name;

	//private boolean isMarketable;

	@OneToMany(cascade= CascadeType.ALL, mappedBy="id.sku")
	private List<Warehouse> listWarehouses;

	
	public Produto() {
	}


	
	public boolean isMarketable() {
		if(listWarehouses != null && listWarehouses.size()>0) {
			for(Warehouse warehouse:   listWarehouses ) {
				if(warehouse.getQuantity()>0) {
					return true;
				}
			}
		}
		else {
			return false;
		}
		return false;
	}
	
	
	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Warehouse> getListWarehouses() {
		return listWarehouses;
	}

	public void setListWarehouses(List<Warehouse> listWarehouses) {
		this.listWarehouses = listWarehouses;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Produto other = (Produto) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}


	
}
