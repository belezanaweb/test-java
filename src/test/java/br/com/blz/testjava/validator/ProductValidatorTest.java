package br.com.blz.testjava.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.blz.testjava.exception.InvalidMarketableProductException;
import br.com.blz.testjava.exception.InvalidProductNameException;
import br.com.blz.testjava.exception.InvalidQuantityInventoryLinkException;
import br.com.blz.testjava.exception.InvalidTotalProductQuantityException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductValidatorTest {
	
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
	
	@Test(expected=InvalidMarketableProductException.class)
	public void tryValidateProductWithInvalidMarketableStateAndFail() {
		Product prod = new Product();
		prod.setName(PROD_NAME);
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
		ware.setKind(WARE_KIND);
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
		assertEquals(WARE_KIND, warehouse.getKind());
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
		ware.setKind(WARE_KIND);
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
		assertEquals(WARE_KIND, warehouse.getKind());
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
		ware.setKind(WARE_KIND);
		warehousesList.add(ware);
		inventory.setWarehouses(warehousesList);
		
		prod.setInventory(inventory);
		ProductValidator.validate(prod);
	}
}
