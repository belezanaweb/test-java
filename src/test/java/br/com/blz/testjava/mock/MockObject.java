package br.com.blz.testjava.mock;

import java.util.HashSet;
import java.util.Set;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;

public class MockObject {

	private MockObject() {
		
	}
	
	public static Warehouse getOneWarehouse() {
		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("SP");
		warehouse.setQuantity(12);
		warehouse.setType("ECOMMERCE");
		return warehouse;
	}
	
	public static Inventory getInventory() {
		Inventory inventory = new Inventory();
		Set<Warehouse> warehouses = new HashSet<Warehouse>();
		warehouses.add(getOneWarehouse());
		inventory.setQuantity(warehouses.size());
		inventory.setWarehouses(warehouses);
		return inventory;
	}
	
	

	public static Product getProduct() {
		Product product = new Product();
		product.setSku(1010L);
		product.setInventory(getInventory());
		product.setMarketable(Boolean.TRUE);
		product.setName("teste");
		return product;
	}
	
	public static Product getProductWithoutName() {
		Product product = new Product();
		product.setSku(1010L);
		product.setInventory(getInventory());
		product.setMarketable(Boolean.TRUE);
		return product;
	}
}
