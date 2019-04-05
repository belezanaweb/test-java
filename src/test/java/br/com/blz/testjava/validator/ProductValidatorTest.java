package br.com.blz.testjava.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.blz.testjava.exception.DupItemsInWarehousesException;
import br.com.blz.testjava.exception.InvalidProductNameException;
import br.com.blz.testjava.exception.InvalidQuantityInventoryLinkException;
import br.com.blz.testjava.exception.InvalidTotalProductQuantityException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductValidatorTest {
	
	private static final String WARE_KIND_2 = "PHYSICAL_STORE";
	private static final String WARE_KIND = "ECOMMERCE";
	private static final String FIRST_WARE_LOCATION = "SP";
	private static final Boolean NOT_MARKETABLE = Boolean.FALSE;
	private static final String PROD_NAME = "Xampu Cera Ceramidas";

	@Test(expected=InvalidProductNameException.class)
	public void tryValidateProductWithNullNameAndFail() {
		Product prod = new Product();
		ProductValidator.validate(prod);
	}
	
	@Test(expected=InvalidProductNameException.class)
	public void tryValidateProductWithEmptyNameAndFail() {
		Product prod = new Product();
		prod.setName("");
		ProductValidator.validate(prod);
	}
	
	@Test
	public void testValidateProductNullInventory() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		ProductValidator.validate(prod);
		
		assertEquals(PROD_NAME, prod.getName());
		assertFalse(prod.isIsMarketable());
	}
	
	@Test
	public void testValidateProductWithInventory() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		prod.setInventory(new Inventory());
		ProductValidator.validate(prod);
		
		assertEquals(PROD_NAME, prod.getName());
		assertFalse(prod.isIsMarketable());
		assertNotNull(prod.getInventory());
		assertTrue(prod.getInventory().getQuantity().equals(Long.valueOf(0)));
	}
	
	@Test(expected=InvalidTotalProductQuantityException.class)
	public void testValidateProductWithInventoryAndNegativeQuantityAndFail() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		Inventory inventory = new Inventory();
		inventory.setQuantity(-1L);
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test
	public void testValidateProductWithInventoryAndNoItemsinWarehouses() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(0L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		warehousesList.add(ware);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
		
		assertEquals(PROD_NAME, prod.getName());
		assertFalse(prod.isIsMarketable());
		assertNotNull(prod.getInventory());
		assertTrue(prod.getInventory().getQuantity().equals(Long.valueOf(0)));
		
		Warehouse warehouse = warehousesList.get(0);
		assertEquals(Long.valueOf(0), warehouse.getQuantity());
		assertEquals(FIRST_WARE_LOCATION, warehouse.getLocality());
		assertEquals(WARE_KIND, warehouse.getType());
	}
	
	@Test
	public void testValidateProductWithInventoryContainingQuantityAndItemsInWarehouses() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(9L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		warehousesList.add(ware);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
		
		assertEquals(PROD_NAME, prod.getName());
		assertFalse(prod.isIsMarketable());
		assertNotNull(prod.getInventory());
		assertTrue(prod.getInventory().getQuantity().equals(Long.valueOf(9)));
		
		Warehouse warehouse = warehousesList.get(0);
		assertEquals(Long.valueOf(9), warehouse.getQuantity());
		assertEquals(FIRST_WARE_LOCATION, warehouse.getLocality());
		assertEquals(WARE_KIND, warehouse.getType());
	}
	
	@Test(expected=InvalidQuantityInventoryLinkException.class)
	public void testValidateProductWithInventoryWithoutQuantityAndItemsInWarehouses() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		warehousesList.add(ware);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test(expected=DupItemsInWarehousesException.class)
	public void tryValidateProductWithInventoryContainingSameWarehousesAndFail() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(0L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(-9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(WARE_KIND);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test(expected=InvalidQuantityInventoryLinkException.class)
	public void tryValidateProductWithInventoryContainingQuantityAndItemsInWarehousesAndFail() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(0L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(-9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(WARE_KIND_2);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test
	public void tryValidateProductWithInventoryWithItemsInWarehouses() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(18L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(WARE_KIND_2);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test
	public void tryValidateProductWithInventoryWithItemsInWarehousesNullLocation1() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(18L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(null);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(WARE_KIND_2);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test
	public void tryValidateProductWithInventoryWithItemsInWarehousesNullLocation2() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(18L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(null);
		ware2.setType(WARE_KIND_2);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test
	public void tryValidateProductWithInventoryWithItemsInWarehousesNullType1() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(18L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(null);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(WARE_KIND_2);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test
	public void tryValidateProductWithInventoryWithItemsInWarehousesNullType2() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(18L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(null);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test(expected=DupItemsInWarehousesException.class)
	public void tryValidateProductWithInventoryWithItemsInWarehousesNullFields() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(18L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(9L);
		ware.setLocality(null);
		ware.setType(null);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(null);
		ware2.setType(null);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test
	public void tryValidateProductWithInventoryWithItemsInWarehousesNullQuantityFirstWarehouse() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(9L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(null);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
	
	@Test(expected=InvalidQuantityInventoryLinkException.class)
	public void tryValidateProductWithInventoryWithItemsInWarehousesNegativeQuantityFirstWarehouse() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
		prod.setIsMarketable(NOT_MARKETABLE);
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(8L);
		
		List<Warehouse> warehousesList = new ArrayList();
		Warehouse ware = new Warehouse();
		ware.setQuantity(-1L);
		ware.setLocality(FIRST_WARE_LOCATION);
		ware.setType(WARE_KIND);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(9L);
		ware2.setLocality(FIRST_WARE_LOCATION);
		ware2.setType(null);
		
		warehousesList.add(ware);
		warehousesList.add(ware2);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
}
