package br.com.blz.adapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.blz.model.InventoryModel;
import br.com.blz.model.ProductModel;
import br.com.blz.model.WarehouseModel;

public class ProductAdapter {

	/**
	 * Construtor privado para evitar instâncias
	 */
	private ProductAdapter() {
	}
	
	/**
	 * Adapta o object recebido na requisição para o modelo
	 * 
	 * @param obj
	 * @return {@link ProductModel}
	 */
	@SuppressWarnings("unchecked")
	public static ProductModel toModel(final Object obj) {
		ProductModel p = new ProductModel();
		
		LinkedHashMap<String, Object> hashMap = (LinkedHashMap<String, Object>) obj;
		
		Integer sku = hashMap.get("sku") != null ? new Integer(hashMap.get("sku").toString()) : 0;
		p.setSku(sku);
		
		String name = hashMap.get("name") != null ? hashMap.get("name").toString() : null;
		p.setName(name);
		
		InventoryModel inventory = ProductAdapter.getInventory(hashMap);
		p.setInventory(inventory);
		
		return p;
	}
	
	/**
	 * Recupera o obj Inventory
	 * 
	 * @param hashMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static InventoryModel getInventory(final LinkedHashMap<String, Object> hashMap) {
		
		InventoryModel inventory = new InventoryModel();
		
		LinkedHashMap<String, Object> hashMapInventory = (LinkedHashMap<String, Object>) hashMap.get("inventory");
		
		List<LinkedHashMap<String, Object>> listWarehouses = (List<LinkedHashMap<String, Object>>) hashMapInventory.get("warehouses");
		
		List<WarehouseModel> warehouses = ProductAdapter.getWarehouses(listWarehouses);
		
		inventory.setWarehouses(warehouses);
		
		return inventory;
	}
	
	/**
	 * Recupera a lista de Warehouse
	 * 
	 * @param listWarehouses
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static List<WarehouseModel> getWarehouses(List<LinkedHashMap<String, Object>> listWarehouses) {
		List<WarehouseModel> list = new ArrayList<WarehouseModel>();
		
		for(LinkedHashMap warehouse: listWarehouses) {
			WarehouseModel newWarehouse = new WarehouseModel();
			
			if(warehouse.get("locality") != null) {
				newWarehouse.setLocality(warehouse.get("locality").toString());
			}
			
			if(warehouse.get("quantity") != null) {
				newWarehouse.setQuantity(new Integer(warehouse.get("quantity").toString()));
			}
			
			if(warehouse.get("type") != null) {
				newWarehouse.setType(warehouse.get("type").toString());
			}
			
			list.add(newWarehouse);
		}
		
		return list;
	}
}