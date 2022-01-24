package br.com.blz.testjava;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import br.com.blz.testjava.domain.entities.Inventory;
import br.com.blz.testjava.domain.entities.Warehouse;

public class InventoryTests {
	
	public InventoryTests() {
		
	}
	
	
	@DisplayName("Verify if the inventory quantity is being well calculated")
	@Test
	public void testQuantityUpdate() {
		
		Warehouse warehouse1 = new Warehouse("SP", 10, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 1, "LOJA FISICA");
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		assertEquals(inventory.getQuantity(), 11);
		
	}

}
