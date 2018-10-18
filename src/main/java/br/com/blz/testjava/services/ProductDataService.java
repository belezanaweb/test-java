package br.com.blz.testjava.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.dominio.Inventory;
import br.com.blz.testjava.dominio.Product;
import br.com.blz.testjava.dominio.WareHouses;
import br.com.blz.testjava.dominio.enums.TypeWareHouse;
import br.com.blz.testjava.services.exceptions.ObjectNotFoundException;

/**
 * 
 * @author Hermes
 *
 *	Classe criada para gerenciar os Sku´s em memória.
 *
 */

@Service
public class ProductDataService {
	
	private Map<Long,Product> mapSku = new HashMap<Long,Product>();
	
	public ProductDataService() {
		
		// Iniciando a lista com um Sku registrado.
		
		Inventory inventory = new Inventory();
		inventory.getWareHouses().add(new WareHouses("SP", 12, TypeWareHouse.ECOMMERCE));
		inventory.getWareHouses().add(new WareHouses("MOEMA", 3, TypeWareHouse.PHYSICAL_STORE));
		
		Product sku = new Product(
				43264L, 
				"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", 
				inventory);
		
		this.mapSku.put(sku.getSku(), sku);
		
	}
	
	public Product findBySku(Long sku) {		
		return this.mapSku.get(sku) ;
	}
	
	public List<Product> findAll() {
		return new ArrayList(this.mapSku.values());
	}
	
	public Product save(Product obj) {
		this.mapSku.put(obj.getSku(), obj);
		return obj;
	}
	
	public void delete(Long sku) {
		this.mapSku.remove(sku);
	}

}
