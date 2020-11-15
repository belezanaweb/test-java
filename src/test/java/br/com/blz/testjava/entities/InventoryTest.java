package br.com.blz.testjava.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class InventoryTest {

	@Test
	void getQuantityTest() {
		var inventory = new Inventory();
		inventory.setWarehouses(createWarehouses());
		assertEquals(5, inventory.getQuantity());
	}
	
	private List<Warehouse> createWarehouses(){
		var warehouses = new ArrayList<Warehouse>();
		var warehouse1 = new Warehouse();
		warehouse1.setQuantity(2);
		var warehouse2 = new Warehouse();
		warehouse2.setQuantity(3);
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		return warehouses;
	}
}
