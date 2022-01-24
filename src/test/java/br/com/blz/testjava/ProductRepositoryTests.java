package br.com.blz.testjava;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import br.com.blz.testjava.data.repositories.ProductRepositoryImpl;
import br.com.blz.testjava.domain.entities.Inventory;
import br.com.blz.testjava.domain.entities.Product;
import br.com.blz.testjava.domain.entities.Warehouse;


public class ProductRepositoryTests {
	
	
	private ProductRepositoryImpl productRespository = new ProductRepositoryImpl();
	
	public ProductRepositoryTests() {
		
	}
	
	@Test
	public void testAddProduct() {
		Warehouse warehouse1 = new Warehouse("SP", 10, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 1, "LOJA FISICA");
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		Product product = new Product("999999", "Produto de testes", inventory);
		
		
		String sku = this.productRespository.add(product);
		
		assertEquals(sku, "999999");
		
	}
	
	@Test
	public void testDeleteProductTrue() {
		Warehouse warehouse1 = new Warehouse("SP", 10, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 1, "LOJA FISICA");
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		Product product = new Product("999999", "Produto de testes", inventory);
		String skuInserted = this.productRespository.add(product);
		
		Boolean isDeleted = this.productRespository.delete(skuInserted);
		
		assertEquals(isDeleted, true);
		
	}
	
	@Test
	public void testDeleteProductFalse() {
		Boolean isDeleted = this.productRespository.delete("888888");
		assertEquals(isDeleted, false);
	}
	
	@Test
	public void testFindProductBySku() {
		Warehouse warehouse1 = new Warehouse("SP", 10, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 1, "LOJA FISICA");
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		Product product = new Product("999999", "Produto de testes", inventory);
		String skuInserted = this.productRespository.add(product);
		
		Product productFound = this.productRespository.find(skuInserted);
		
		assertEquals(productFound.getName(), "Produto de testes");
		assertEquals(productFound.getSku(), "999999");
		
	}
	
	@Test
	public void testFindProductBySkuFalse() {
		Warehouse warehouse1 = new Warehouse("SP", 10, "ECOMMERCE");
		Warehouse warehouse2 = new Warehouse("MOEMA", 1, "LOJA FISICA");
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		Product product = new Product("999999", "Produto de testes", inventory);
		this.productRespository.add(product);
		
		Product productFound = this.productRespository.find("8888888");
		
		assertEquals(productFound, null);
		
		
	}
	
}
