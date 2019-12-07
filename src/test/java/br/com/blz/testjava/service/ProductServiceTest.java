package br.com.blz.testjava.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.blz.testjava.TestJavaApplication;
import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.Warehouses;
import br.com.blz.testjava.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJavaApplication.class })
public class ProductServiceTest {

	@Autowired
	ProductService service;
	
	@Autowired
	ProductRepository repository;
	
	@After
	public void initializeTests() {
		service.repository = repository;
	}

	@Test
	public void testGetProduct() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(10);
		service.repository.createProduct(product);
		ProductDTO pd = service.getProduct(product.getSku());
		assertEquals(pd, product);
	}
	
	@Test
	public void testGetProductNotExists() {
		assertNull(service.getProduct(11));
	}
	
	@Test
	public void testGetProductWithoutInventory() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(12);
		service.repository.createProduct(product);
		ProductDTO pd = service.getProduct(product.getSku());
		assertEquals(pd.getInventory().getQuantity(), 0);
	}
	
	@Test
	public void testGetProductWithoutWarehouse() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(13);
		product.setInventory(new InventoryDTO());
		service.repository.createProduct(product);
		ProductDTO pd = service.getProduct(product.getSku());
		assertEquals(pd.getInventory().getQuantity(), 0);
	}
	
	@Test
	public void testGetProductEmptyWarehouse() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(14);
		product.setInventory(new InventoryDTO());
		product.getInventory().setWarehouses(new ArrayList<Warehouses>());
		service.repository.createProduct(product);
		ProductDTO pd = service.getProduct(product.getSku());
		assertEquals(pd.getInventory().getQuantity(), 0);
	}
	
	@Test
	public void testGetProductZeroQuantityWarehouse() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(15);
		product.setInventory(new InventoryDTO());
		ArrayList<Warehouses> list = new ArrayList<Warehouses>();
		Warehouses warehouse = new Warehouses();
		warehouse.setQuantity(0);
		list.add(warehouse);
		product.getInventory().setWarehouses(list);
		service.repository.createProduct(product);
		ProductDTO pd = service.getProduct(product.getSku());
		assertEquals(pd.getInventory().getQuantity(), 0);
	}
	
	@Test
	public void testGetProductWithQuantity() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(16);
		product.setInventory(new InventoryDTO());
		ArrayList<Warehouses> list = new ArrayList<Warehouses>();
		Warehouses warehouse = new Warehouses();
		warehouse.setQuantity(1);
		list.add(warehouse);
		product.getInventory().setWarehouses(list);
		service.repository.createProduct(product);
		ProductDTO pd = service.getProduct(product.getSku());
		assertEquals(pd.getInventory().getQuantity(), 1);
	}
	
	@Test
	public void testGetProductWithNegativeQuantity() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(17);
		product.setInventory(new InventoryDTO());
		ArrayList<Warehouses> list = new ArrayList<Warehouses>();
		Warehouses warehouse = new Warehouses();
		//quando a quantidade é negativa estou ignorando o valor e forçando o zero
		warehouse.setQuantity(-1);
		list.add(warehouse);
		product.getInventory().setWarehouses(list);
		service.repository.createProduct(product);
		ProductDTO pd = service.getProduct(product.getSku());
		assertEquals(pd.getInventory().getQuantity(), 0);
	}


	@Test
	public void testRemoveProductExists() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(1);
		service.repository.createProduct(product);
		ProductDTO pd = service.removeProduct(product.getSku());
		assertNotNull(pd);
	}

	@Test
	public void testRemoveProductNotExists() {
		ProductDTO product = new ProductDTO();
		product.setSku(2);
		ProductDTO pd = service.removeProduct(product.getSku());
		assertNull(pd);
	}

	@Test
	public void testCreateProduct() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(3);
		service.createProduct(product);
		assertEquals(product, service.repository.getProduct(product.getSku()));	
	}
	
	@Test(expected=Exception.class)
	public void testCreateProductExists() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(4);
		service.createProduct(product);
		ProductDTO product2 = new ProductDTO();
		product2.setSku(4);
		service.createProduct(product2);
	}

	@Test
	public void testUpdateProductExists() throws Exception {
		ProductDTO product = new ProductDTO();
		product.setSku(6);
		product.setName("Blz na Web");
		repository.createProduct(product);
		ProductDTO product2 = new ProductDTO();
		product2.setSku(6);
		product2.setName("Beleza Na Web");
		service.updateProduct(product2);
		assertEquals(product2.getName(), repository.getProduct(product.getSku()).getName());
	}
	
	@Test
	public void testUpdateProductNotExists() {
		ProductDTO product = new ProductDTO();
		product.setSku(5);
		ProductDTO pd = service.updateProduct(product);
		assertNull(pd);
	}

}
