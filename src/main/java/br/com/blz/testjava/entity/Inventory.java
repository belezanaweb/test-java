package br.com.blz.testjava.entity;

import java.io.Serializable;
import java.util.List;

//Anotacoes de persistencia nao incluidas
//sem valor de quantidade para persistir
public class Inventory implements Serializable {

	private static final long serialVersionUID = 7863816388865372185L;
	
	List<Warehouse> warehouses;

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
