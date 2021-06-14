package br.com.blz.testjava.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.blz.testjava.enums.WarehouseTypeEnum;

public class ProductTest {
	
	@DisplayName("Product test is marketable.")
	@Test
	public void productIsMarketableTest() {
		Product product = new Product();
		product.setSku(101060L);
		product.setName("Novo Produto de Testes");
		
		Inventory inventory = new Inventory();
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		Warehouse warehouse1 = new Warehouse("PR", 10, WarehouseTypeEnum.PHYSICAL_STORE);
		Warehouse warehouse2 = new Warehouse("SP", 15, WarehouseTypeEnum.ECOMMERCE);
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		inventory.setWarehouses(warehouses);
		product.setInventory(inventory);
		
		assertTrue(product.getIsMarketable().equals(Boolean.TRUE));
	}
	
	@DisplayName("Product test is not marketable.")
	@Test
	public void productIsNotMarketableTest() {
		Product product = new Product();
		product.setSku(101060L);
		product.setName("Novo Produto de Testes Not Marktable");
		
		Inventory inventory = new Inventory();
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		Warehouse warehouse1 = new Warehouse("PR", 0, WarehouseTypeEnum.PHYSICAL_STORE);
		Warehouse warehouse2 = new Warehouse("SP", 0, WarehouseTypeEnum.ECOMMERCE);
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		inventory.setWarehouses(warehouses);
		product.setInventory(inventory);
		
		assertTrue(product.getIsMarketable().equals(Boolean.FALSE));
	}
	
}
