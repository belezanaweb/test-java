package br.com.blz.testjava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.error.NoProductResultException;
import br.com.blz.testjava.error.ProductSavedException;
import br.com.blz.testjava.inventory.Inventory;
import br.com.blz.testjava.product.Product;
import br.com.blz.testjava.product.ProductService;
import br.com.blz.testjava.warehouse.Warehouse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTests {

	@Autowired
	private ProductService service;
	
	@Test
	public void saveTest() {
		Product product = new Product();
		product.setSku(43264L);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		
		Inventory inventory = new Inventory();
		
		Warehouse warehouseEcommerce = new Warehouse();
		warehouseEcommerce.setLocality("SP");
		warehouseEcommerce.setQuantity(12);
		warehouseEcommerce.setType("ECOMMERCE");
		
		Warehouse warehouseStore = new Warehouse();
		warehouseStore.setLocality("MOEMA");
		warehouseStore.setQuantity(3);
		warehouseStore.setType("PHYSICAL_STORE");
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouseEcommerce);
		warehouses.add(warehouseStore);
		
		inventory.setWarehouses(warehouses);	
		
		product.setInventory(inventory);
		
		try {
			product = service.saveProduct(product);
		} catch (ProductSavedException e) {
			e.printStackTrace();
		}
		
		assertNotNull(product);
		
		service.deleteBySku(product.getSku());
	
	}
	
	@Test
	public void getBySkuTest() {
		Product product = new Product();
		product.setSku(43264L);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		
		Inventory inventory = new Inventory();
		
		Warehouse warehouseEcommerce = new Warehouse();
		warehouseEcommerce.setLocality("SP");
		warehouseEcommerce.setQuantity(12);
		warehouseEcommerce.setType("ECOMMERCE");
		
		Warehouse warehouseStore = new Warehouse();
		warehouseStore.setLocality("MOEMA");
		warehouseStore.setQuantity(3);
		warehouseStore.setType("PHYSICAL_STORE");
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouseEcommerce);
		warehouses.add(warehouseStore);
		
		inventory.setWarehouses(warehouses);	
		
		product.setInventory(inventory);
		
		try {
			service.saveProduct(product);
		} catch (ProductSavedException e) {
			e.printStackTrace();
		}
		
		Product productTest = service.getBySku(43264L);
		
		assertNotNull(productTest);
		
		service.deleteBySku(productTest.getSku());
	
	}
	
	@Test
	public void editTest() {
		Product product = new Product();
		product.setSku(43264L);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		
		Inventory inventory = new Inventory();
		
		Warehouse warehouseEcommerce = new Warehouse();
		warehouseEcommerce.setLocality("SP");
		warehouseEcommerce.setQuantity(12);
		warehouseEcommerce.setType("ECOMMERCE");
		
		Warehouse warehouseStore = new Warehouse();
		warehouseStore.setLocality("MOEMA");
		warehouseStore.setQuantity(3);
		warehouseStore.setType("PHYSICAL_STORE");
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouseEcommerce);
		warehouses.add(warehouseStore);
		
		inventory.setWarehouses(warehouses);	
		
		product.setInventory(inventory);
		
		try {
			product = service.saveProduct(product);
		} catch (ProductSavedException e) {
			e.printStackTrace();
		}
		
		Product productTest = service.getBySku(43264L);
		
		
		productTest.setName(productTest.getName() + " edited");
		
		try {
			service.update(productTest);
		} catch (NoProductResultException e) {
			e.printStackTrace();
		}
		
		Product ProductEdited = service.getBySku(43264L);
		
		assertEquals("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g edited", ProductEdited.getName());
		
		service.deleteBySku(ProductEdited.getSku());
	
	}
	
	@Test
	public void deleteTest() {
		Product product = new Product();
		product.setSku(43264L);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		
		Inventory inventory = new Inventory();
		
		Warehouse warehouseEcommerce = new Warehouse();
		warehouseEcommerce.setLocality("SP");
		warehouseEcommerce.setQuantity(12);
		warehouseEcommerce.setType("ECOMMERCE");
		
		Warehouse warehouseStore = new Warehouse();
		warehouseStore.setLocality("MOEMA");
		warehouseStore.setQuantity(3);
		warehouseStore.setType("PHYSICAL_STORE");
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouseEcommerce);
		warehouses.add(warehouseStore);
		
		inventory.setWarehouses(warehouses);	
		
		product.setInventory(inventory);
		
		try {
			service.saveProduct(product);
		} catch (ProductSavedException e) {
			e.printStackTrace();
		}
		
		Product productTest = service.getBySku(43264L);
		
		service.deleteBySku(productTest.getSku());
		
		Product productDeleted = service.getBySku(43264L);
		
		assertNull(productDeleted);
	}

}
