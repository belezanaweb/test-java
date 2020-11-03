package br.com.blz.testjava.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.blz.testjava.domain.exception.ProductAlreadyExistException;
import br.com.blz.testjava.domain.exception.ProductNotFoundException;
import br.com.blz.testjava.domain.model.Inventory;
import br.com.blz.testjava.domain.model.Product;
import br.com.blz.testjava.domain.model.Warehouse;
import br.com.blz.testjava.domain.repository.ProductRepository;
import br.com.blz.testjava.domain.service.ProductServiceImpl;

public class ProductServiceTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepositoryMocked;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		Product productInDatabase = new Product();
		Inventory inventoryInDatabase = new Inventory();
		List<Warehouse> warehousesInDatabase = new ArrayList<>();
		Warehouse warehouseInDatabase = new Warehouse();
		
		warehouseInDatabase.setId(1l);
		warehouseInDatabase.setLocality("Moema");
		warehouseInDatabase.setQuantity(12);
		warehouseInDatabase.setType("Ecommerce");
		
		warehousesInDatabase.add(warehouseInDatabase);
		
		inventoryInDatabase.setId(1L);
		inventoryInDatabase.setWarehouses(warehousesInDatabase);
		
		productInDatabase.setSku(1L);
		productInDatabase.setName("Shampoo Loreal");
		productInDatabase.setInventory(inventoryInDatabase);
		
		Product newProduct = new Product();
		newProduct.setSku(2L);
		newProduct.setName("Shampoo Boticario");
		newProduct.setInventory(inventoryInDatabase);
		
		Product productToUpdate = new Product();
		productToUpdate.setSku(3L);
		productToUpdate.setName("Shampoo Loreal");
		productToUpdate.setInventory(inventoryInDatabase);
		
		List<Product> productList = new ArrayList<>();
		productList.add(productInDatabase);
		productList.add(newProduct);
		productList.add(productToUpdate);
		
		when(productRepositoryMocked.findBySku(1L)).thenReturn(Optional.of(productInDatabase));
		when(productRepositoryMocked.findBySku(3L)).thenReturn(Optional.of(productToUpdate));
		when(productRepositoryMocked.save(newProduct)).thenReturn(productInDatabase);
		when(productRepositoryMocked.findAll()).thenReturn(productList);
	}
	
	@Test(expected = ProductAlreadyExistException.class)
	public void shouldDeny_CreationOfProduct_ThatExists() {
		Product newProduct = new Product();
		Inventory newInventory = new Inventory();
		List<Warehouse> newWarehouses = new ArrayList<>();
		Warehouse newWarehouse = new Warehouse();
		
		newWarehouse.setId(1l);
		newWarehouse.setLocality("Moema");
		newWarehouse.setQuantity(12);
		newWarehouse.setType("Ecommerce");
		
		newWarehouses.add(newWarehouse);
		
		newInventory.setId(1L);
		newInventory.setWarehouses(newWarehouses);
		
		newProduct.setSku(1L);
		newProduct.setName("Shampoo Loreal");
		newProduct.setInventory(newInventory );
		
		productService.save(newProduct);
	}
	
	@Test
    public void shouldCreate_newProduct() {
		Product newProduct = new Product();
		Inventory newInventory = new Inventory();
		List<Warehouse> newWarehouses = new ArrayList<>();
		Warehouse newWarehouse = new Warehouse();
		
		newWarehouse.setId(1l);
		newWarehouse.setLocality("Moema");
		newWarehouse.setQuantity(12);
		newWarehouse.setType("Ecommerce");
		
		newWarehouses.add(newWarehouse);
		
		newInventory.setId(1L);
		newInventory.setWarehouses(newWarehouses);
		
		newProduct.setSku(2L);
		newProduct.setName("Shampoo Loreal");
		newProduct.setInventory(newInventory );
		
		productService.save(newProduct);
		
		assertThat(newProduct.getSku(), equalTo(2L));
	}
	
	@Test
	public void shouldUpdate_ProductName() {
		Product productToUpdate = new Product();
		Inventory inventoryToUpdate = new Inventory();
		List<Warehouse> warehousesToUpdate = new ArrayList<>();
		Warehouse warehouseToUpdate = new Warehouse();
		
		warehouseToUpdate.setId(1l);
		warehouseToUpdate.setLocality("Moema");
		warehouseToUpdate.setQuantity(12);
		warehouseToUpdate.setType("Ecommerce");
		
		warehousesToUpdate.add(warehouseToUpdate);
		
		inventoryToUpdate.setId(1L);
		inventoryToUpdate.setWarehouses(warehousesToUpdate);
		
		productToUpdate.setSku(3L);
		productToUpdate.setName("Shampoo BLZ");
		productToUpdate.setInventory(inventoryToUpdate );
		
		productService.update(productToUpdate);
		
		assertThat(productToUpdate.getName(), equalTo("Shampoo BLZ"));
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void shouldFind_Product_ThatNotExists() {
		Product product = new Product();
		productService.findBySku(product.getSku());
	}
	
	@Test
	public void shouldRemove_Product_ThatExists() {
		Product productToRemove = new Product();
		productToRemove.setSku(1L);
		productToRemove.setName("Shampoo Loreal");
		
		productService.remove(productToRemove.getSku());
		
		verify(productRepositoryMocked).delete(productToRemove);
	}
	
	@Test
	public void shouldReturn_ProductList() {
		List<Product> productList = productService.findAll();
		assertEquals(3, productList.size());
	}
}
