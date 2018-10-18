package br.com.blz.testjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.dominio.Inventory;
import br.com.blz.testjava.dominio.Sku;
import br.com.blz.testjava.dominio.WareHouses;
import br.com.blz.testjava.dominio.enums.TypeWareHouse;

@Service
public class SkuService {
	
	public Sku findBySku(Integer id) {
		
		Inventory inventory = new Inventory();
		inventory.getWareHouses().add(new WareHouses("SP", 12, TypeWareHouse.ECOMMERCE));
		inventory.getWareHouses().add(new WareHouses("MOEMA", 3, TypeWareHouse.PHYSICAL_STORE));
		
		Sku sku = new Sku(
				43264L, 
				"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", 
				inventory);
		return sku;
	}
	
	public List<Sku> findAll() {
		
		Inventory inventory = new Inventory();
		inventory.getWareHouses().add(new WareHouses("SP", 12, TypeWareHouse.ECOMMERCE));
		inventory.getWareHouses().add(new WareHouses("MOEMA", 3, TypeWareHouse.PHYSICAL_STORE));
		
		Sku sku = new Sku(
				43264L, 
				"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", 
				inventory);
		List<Sku> listSku = new ArrayList<>();
		listSku.add(sku);
		
		return listSku;
	}
	
}
