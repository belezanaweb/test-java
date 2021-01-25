package br.com.blz.testjava.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {

	@BeforeEach
	public void setup() {
	}
	
	@Test
	void successQuantityBiggestZeroWhenContainsProduct() {	
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();

		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("SP");
		warehouse.setQuantity(12);
		warehouse.setType(WarehousesType.ECOMMERCE);
		warehouses.add(warehouse);

		warehouse = new Warehouse();
		warehouse.setLocality("MOEMA");
		warehouse.setQuantity(3);
		warehouse.setType(WarehousesType.PHYSICAL_STORE);
		warehouses.add(warehouse);

		Inventory inventory = new Inventory(warehouses);
		
		assertTrue(inventory.getQuantity() > 0);
	}
	
	@Test
	void successQuantityEqualZeroWhenNotContainsProduct() {	
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();

		Inventory inventory = new Inventory(warehouses);

		assertTrue(inventory.getQuantity() == 0);
	}
	
}
