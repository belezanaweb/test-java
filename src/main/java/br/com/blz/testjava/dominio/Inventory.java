package br.com.blz.testjava.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<WareHouses> wareHouses = new ArrayList<>();
    
	public Inventory() {		
	
	}

	public List<WareHouses> getWareHouses() {
		return wareHouses;
	}

	public void setWareHouses(List<WareHouses> wareHouses) {
		this.wareHouses = wareHouses;
	}
}
