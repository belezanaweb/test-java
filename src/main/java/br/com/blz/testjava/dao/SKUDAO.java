package br.com.blz.testjava.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.SKU;

@Repository
public class SKUDAO {

	// DAO fake, persistindo em memoria 
	private HashMap<Integer, SKU> database = new HashMap<>();

	public List<SKU> getAll() throws Exception {
		List<SKU> list = new ArrayList<>();
		list.addAll(database.values());
		return list;
	}
	
	public Boolean insert(SKU sku) throws Exception {
		if (database.get(sku.getSku()) != null) {
			return false;
		} else {
			database.put(sku.getSku(), sku);
			return true;
		}
	}
	
	public Boolean update(Integer key, SKU sku) throws Exception {
		if (database.get(key) == null) {
			return false;
		} else {
			database.replace(sku.getSku(), sku);
			return true;
		}
	}
	
	public SKU get(Integer key) throws Exception {
		return database.get(key);
	}
	
	public SKU delete(Integer key) throws Exception {
		return database.remove(key);
	}
}
