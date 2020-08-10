package br.com.blz.produtos.apirest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_PRODUTO")
public class EntityProduto implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	private long sku;
	private String name;
	@OneToMany(targetEntity = EntityWarehouse.class, cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "sku", referencedColumnName = "sku")
	private List<EntityWarehouse> warehouses = new ArrayList<EntityWarehouse>();

	public long getSku() {
		return sku;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EntityWarehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<EntityWarehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	

}
