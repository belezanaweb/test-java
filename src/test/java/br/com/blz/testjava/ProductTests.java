package br.com.blz.testjava;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import br.com.blz.testjava.domain.entities.Inventory;
import br.com.blz.testjava.domain.entities.Product;
import br.com.blz.testjava.domain.entities.Warehouse;

public class ProductTests {
	public ProductTests() {
		
	}
	
	@DisplayName("Verify if isMarketable is set true when inventory.quantity is greater than 0")
	@Test
	public void testIsMarketableTrue() {
		
		Warehouse warehouse1 = new Warehouse("SP", 10, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 1, "LOJA FISICA");
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		Product product = new Product("999999", "Produto de testes", inventory);
		
		assertEquals(product.getIsMarketable(), true);
		
		
	}
	
	@DisplayName("Verify if isMarketable is set false when inventory.quantity is equal 0")
	@Test
	public void testIsMarketableFalse() {
		
		Warehouse warehouse1 = new Warehouse("SP", 0, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 0, "LOJA FISICA");
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		Product product = new Product("999999", "Produto de testes", inventory);
		
		assertEquals(product.getIsMarketable(), false);
		
		
	}

}
