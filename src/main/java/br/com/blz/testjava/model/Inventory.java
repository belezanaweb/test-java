package br.com.blz.testjava.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Warehouse> warehouses;
	

	public Long getQuantity() {
		if(warehouses == null) {
			return 0L;
		}
		Long quantidade = Long.valueOf(0);
		for(Warehouse warehouse : warehouses) {
			if(warehouse.getQuantity() != null) {
				quantidade = Long.sum(quantidade, warehouse.getQuantity());
			}
		}
		return quantidade;
	}
	
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
}
