package br.com.blz.testjava.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

    private Long SKU = (long) 43264;
    private String NAME = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";

	@BeforeEach
	public void setup() {
	}
	
	@Test
	void successIsMarketableTrueWhenAddNewProduct() {	
		
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
		Product product = new Product(SKU, NAME, inventory);
		
		assertTrue(product.getIsMarketable());
	}
	
	@Test
	void successIsMarketableFalseWhenNotContainsProduct() {	
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();

		Inventory inventory = new Inventory(warehouses);
		Product product = new Product(SKU, NAME, inventory);
		
		assertFalse(product.getIsMarketable());
	}
	
}
