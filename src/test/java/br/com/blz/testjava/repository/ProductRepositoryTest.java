package br.com.blz.testjava.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Before;
import org.junit.Test;

import br.com.blz.testjava.exception.InvalidIdException;
import br.com.blz.testjava.exception.InvalidProductNameException;
import br.com.blz.testjava.exception.NullProductException;
import br.com.blz.testjava.exception.ProductIdAlreadyInUseException;
import br.com.blz.testjava.exception.ProductNotExistentException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

public class ProductRepositoryTest {
	
	private static final String WARE_LOCALITY_2 = "SC";
	private static final String WARE_LOCALITY = "SP";
	private static final String WARE_TYPE = "ECOMMERCE";
	private static AtomicLong id = new AtomicLong(1);
	private static final String PRODUCT_NAME = "Sabonete Dove";
	
	private ProductRepository repo;
	
	@Before
	public void init() {
		repo = new ProductRepository();
	}
	
	@Test(expected=NullProductException.class)
	public void tryInsertNull() {
		repo.insert(null);
	}
	
	@Test(expected=InvalidProductNameException.class)
	public void tryInsertProductWithoutName() {
		repo.insert(new Product());
	}
	
	@Test(expected=InvalidIdException.class)
	public void tryInsertProductWithNameButNegativeId() {
		Product newProduct = new Product();
		newProduct.setSku(Long.MIN_VALUE);
		repo.insert(newProduct);
	}
	
	@Test(expected=InvalidProductNameException.class)
	public void tryInsertProductWhiIdAndWithoutName() {
		Product newProduct = new Product();
		newProduct.setSku(id.getAndIncrement());
		repo.insert(newProduct);
	}
	
	@Test
	public void insertProduct() {
		Product newProduct = new Product();
		newProduct.setSku(id.getAndIncrement());
		newProduct.setName(PRODUCT_NAME);
		Product productInserted = repo.insert(newProduct);
		
		assertEquals(PRODUCT_NAME, productInserted.getName());
		assertTrue(productInserted.getSku().longValue() > 0);
	}
	
	@Test(expected=ProductIdAlreadyInUseException.class)
	public void tryInsertTwoProductsSameId() {
		Product newProduct = new Product();
		newProduct.setSku(id.getAndIncrement());
		newProduct.setName(PRODUCT_NAME);
		repo.insert(newProduct);
		
		repo.insert(newProduct);
	}
	
	@Test
	public void tryGetProduct() {
		Product newProduct = new Product();
		Long sku = id.getAndIncrement();
		newProduct.setSku(sku);
		newProduct.setName(PRODUCT_NAME);
		repo.insert(newProduct);
		
		Product productInRepo = repo.get(sku);
		assertEquals(PRODUCT_NAME, productInRepo.getName());
		assertEquals(sku, productInRepo.getSku());
		Inventory inventory = productInRepo.getInventory();
		assertNotNull(inventory);
		assertTrue(inventory.getQuantity().longValue() == 0);
		assertEquals(Collections.EMPTY_LIST, inventory.getWarehouses());
	}
	
	@Test
	public void tryGetProductWithOneWarehouse() {
		final long quantity = 10L;
		
		Product newProduct = new Product();
		Long sku = id.getAndIncrement();
		newProduct.setSku(sku);
		newProduct.setName(PRODUCT_NAME);
		Inventory inventory = new Inventory();
		
		inventory.setQuantity(quantity);
		Warehouse ware = new Warehouse();
		ware.setQuantity(quantity);
		ware.setKind(WARE_TYPE);
		ware.setLocality(WARE_LOCALITY);
		inventory.setWarehouses(Arrays.asList(ware));
		newProduct.setInventory(inventory);
		repo.insert(newProduct);
		
		Product productInRepo = repo.get(sku);
		assertEquals(PRODUCT_NAME, productInRepo.getName());
		assertEquals(sku, productInRepo.getSku());
		Inventory inventoryRetrieved = productInRepo.getInventory();
		assertNotNull(inventoryRetrieved);
		assertTrue(inventoryRetrieved.getQuantity().longValue() == quantity);
		Warehouse warehouse = inventoryRetrieved.getWarehouses().get(0);
		assertTrue(quantity == warehouse.getQuantity());
		assertEquals(WARE_TYPE, warehouse.getKind());
		assertEquals(WARE_LOCALITY, warehouse.getLocality());
	}
	
	@Test
	public void tryGetProductWithTwoWarehouses() {
		final long quantity = 10L;
		final long quantity2 = 7L;
		
		Product newProduct = new Product();
		Long sku = id.getAndIncrement();
		newProduct.setSku(sku);
		newProduct.setName(PRODUCT_NAME);
		Inventory inventory = new Inventory();
		
		inventory.setQuantity(quantity+quantity2);
		Warehouse ware = new Warehouse();
		ware.setQuantity(quantity);
		ware.setKind(WARE_TYPE);
		ware.setLocality(WARE_LOCALITY);
		
		Warehouse ware2 = new Warehouse();
		ware2.setQuantity(quantity2);
		ware2.setKind(WARE_TYPE);
		ware2.setLocality(WARE_LOCALITY_2);
		
		inventory.setWarehouses(Arrays.asList(ware, ware2));
		newProduct.setInventory(inventory);
		repo.insert(newProduct);
		
		Product productInRepo = repo.get(sku);
		assertEquals(PRODUCT_NAME, productInRepo.getName());
		assertEquals(sku, productInRepo.getSku());
		
		Inventory inventoryRetrieved = productInRepo.getInventory();
		assertNotNull(inventoryRetrieved);
		assertTrue(inventoryRetrieved.getQuantity().longValue() == quantity + quantity2);
		
		Warehouse warehouse = inventoryRetrieved.getWarehouses().get(0);
		assertTrue(quantity == warehouse.getQuantity());
		assertEquals(WARE_TYPE, warehouse.getKind());
		assertEquals(WARE_LOCALITY, warehouse.getLocality());
		
		Warehouse warehouse2 = inventoryRetrieved.getWarehouses().get(1);
		assertTrue(quantity2 == warehouse2.getQuantity());
		assertEquals(WARE_TYPE, warehouse2.getKind());
		assertEquals(WARE_LOCALITY_2, warehouse2.getLocality());
	}
	
	@Test
	public void updateProduct() {
		Product prod = new Product();
		Long sku = id.getAndIncrement();
		prod.setSku(sku);
		prod.setName(PRODUCT_NAME);
		repo.insert(prod);
		
		final long quantity = 10L;
		
		Product productToUpdate = new Product();
		productToUpdate.setSku(sku);
		productToUpdate.setName(PRODUCT_NAME);
		Inventory inventory = new Inventory();
		
		inventory.setQuantity(quantity);
		Warehouse ware = new Warehouse();
		ware.setQuantity(quantity);
		ware.setKind(WARE_TYPE);
		ware.setLocality(WARE_LOCALITY);
		inventory.setWarehouses(Arrays.asList(ware));
		productToUpdate.setInventory(inventory);
		
		Product updatedProduct = repo.update(productToUpdate);
		
		assertEquals(PRODUCT_NAME, updatedProduct.getName());
		assertEquals(sku, updatedProduct.getSku());
		Inventory inventoryRetrieved = updatedProduct.getInventory();
		assertNotNull(inventoryRetrieved);
		assertTrue(inventoryRetrieved.getQuantity().longValue() == quantity);
		Warehouse warehouse = inventoryRetrieved.getWarehouses().get(0);
		assertTrue(quantity == warehouse.getQuantity());
		assertEquals(WARE_TYPE, warehouse.getKind());
		assertEquals(WARE_LOCALITY, warehouse.getLocality());
	}
	
	@Test(expected=InvalidIdException.class)
	public void tryUpdateProductWithNullIdAndFail() {
		Product prod = new Product();
		Long sku = id.getAndIncrement();
		prod.setSku(sku);
		prod.setName(PRODUCT_NAME);
		repo.insert(prod);
		
		final long quantity = 10L;
		
		Product productToUpdate = new Product();
		productToUpdate.setSku(null);
		productToUpdate.setName(PRODUCT_NAME);
		Inventory inventory = new Inventory();
		
		inventory.setQuantity(quantity);
		Warehouse ware = new Warehouse();
		ware.setQuantity(quantity);
		ware.setKind(WARE_TYPE);
		ware.setLocality(WARE_LOCALITY);
		inventory.setWarehouses(Arrays.asList(ware));
		productToUpdate.setInventory(inventory);
		
		repo.update(productToUpdate);
	}
	
	@Test(expected=ProductNotExistentException.class)
	public void tryUpdateProductWithInvalidIdAndFail() {
		Product prod = new Product();
		Long sku = id.getAndIncrement();
		prod.setSku(sku);
		prod.setName(PRODUCT_NAME);
		repo.insert(prod);
		
		final long quantity = 10L;
		
		Product productToUpdate = new Product();
		productToUpdate.setSku(Long.MAX_VALUE);
		productToUpdate.setName(PRODUCT_NAME);
		Inventory inventory = new Inventory();
		
		inventory.setQuantity(quantity);
		Warehouse ware = new Warehouse();
		ware.setQuantity(quantity);
		ware.setKind(WARE_TYPE);
		ware.setLocality(WARE_LOCALITY);
		inventory.setWarehouses(Arrays.asList(ware));
		productToUpdate.setInventory(inventory);
		
		repo.update(productToUpdate);
	}
	

	@Test(expected=ProductNotExistentException.class)
	public void tryUpdateNullProduct() {
		Product prod = new Product();
		Long sku = id.getAndIncrement();
		prod.setSku(sku);
		prod.setName(PRODUCT_NAME);
		repo.insert(prod);
		
		repo.update(null);
	}
	
	@Test(expected=InvalidIdException.class)
	public void tryUpdateProductWithoutId() {
		Product prod = new Product();
		Long sku = id.getAndIncrement();
		prod.setSku(sku);
		prod.setName(PRODUCT_NAME);
		repo.insert(prod);
		
		repo.update(new Product());
	}
	
	@Test(expected=InvalidIdException.class)
	public void tryUpdateProductWithInvalidId() {
		Product prod = new Product();
		Long sku = id.getAndIncrement();
		prod.setSku(sku);
		prod.setName(PRODUCT_NAME);
		repo.insert(prod);
		
		Product newProduct = new Product();
		newProduct.setSku(Long.MIN_VALUE);
		repo.update(newProduct);
	}
	
	@Test(expected=ProductNotExistentException.class)
	public void tryDeleteNullId() {
		repo.delete(null);
	}
	
	@Test(expected=ProductNotExistentException.class)
	public void tryDeleteZeroId() {
		repo.delete(0L);
	}
	
	@Test(expected=ProductNotExistentException.class)
	public void tryDeleteNegativeId() {
		repo.delete(Long.MIN_VALUE);
	}
	
	@Test(expected=ProductNotExistentException.class)
	public void tryDeleteWithIdNotInBaseYet() {
		repo.delete(Long.MAX_VALUE);
	}
	
	@Test
	public void delete() {
		Product prod = new Product();
		Long sku = id.getAndIncrement();
		prod.setSku(sku);
		prod.setName(PRODUCT_NAME);
		repo.insert(prod);
		
		Product deletedProduct = repo.delete(sku);
		assertEquals(PRODUCT_NAME, deletedProduct.getName());
		assertEquals(sku, deletedProduct.getSku());
	}
}
