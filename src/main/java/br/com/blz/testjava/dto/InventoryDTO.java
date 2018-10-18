package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.blz.testjava.dominio.Inventory;
import br.com.blz.testjava.dominio.WareHouses;

public class InventoryDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<WareHouses> wareHouses = new ArrayList<>();
    
	public InventoryDTO() {		
	
	}
	
	public InventoryDTO(Inventory inventory) {
		this.wareHouses.addAll(inventory.getWareHouses());
	}

	public List<WareHouses> getWareHouses() {
		return wareHouses;
	}

	public void setWareHouses(List<WareHouses> wareHouses) {
		this.wareHouses = wareHouses;
	}
	
	public Integer getQuantity() {
		Integer qtd = 0;
		
		for (WareHouses i : wareHouses) {
			qtd += i.getQuantity();
		}
		return qtd;		
	}
}
