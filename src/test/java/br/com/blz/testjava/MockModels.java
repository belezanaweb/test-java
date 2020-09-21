package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.enums.Types;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.SKU;
import br.com.blz.testjava.model.Warehouse;

public class MockModels {

	public SKU mockSKU1() {
		SKU sku = new SKU();
		Inventory inventory = new Inventory();
		Warehouse warehouse = new Warehouse();
		List<Warehouse> warehouses = new ArrayList<>();
		
		warehouse.setQuantity(5);
		warehouse.setType(Types.PHYSICAL_STORE);
		warehouse.setLocality("CURITIBA");
		
		warehouses.add(warehouse);
		
		inventory.setWarehouses(warehouses);
		
		sku.setName("Produto 1");
		sku.setSku(123);
		sku.setInventory(inventory);
		
		return sku;
	}
	
	public SKU mockSKU2() {
		SKU sku = new SKU();
		Inventory inventory = new Inventory();
		List<Warehouse> warehouses = new ArrayList<>();
		
		Warehouse warehouse = new Warehouse();
		warehouse.setQuantity(15);
		warehouse.setType(Types.ECOMMERCE);
		warehouse.setLocality("SP");
		
		warehouses.add(warehouse);
		
		warehouse = new Warehouse();
		warehouse.setQuantity(32);
		warehouse.setType(Types.ECOMMERCE);
		warehouse.setLocality("RJ");
		
		warehouses.add(warehouse);
		
		inventory.setWarehouses(warehouses);
		
		sku.setName("Produto 2");
		sku.setSku(456);
		sku.setInventory(inventory);
		
		return sku;
	}
	
}