package br.com.blz.testjava.helper;

import br.com.blz.testjava.repository.dao.ProductRepository;
import br.com.blz.testjava.repository.entity.Inventory;
import br.com.blz.testjava.repository.entity.Product;
import br.com.blz.testjava.repository.entity.Warehouse;

public class TestLoader {
	
	public static void loadProducts(ProductRepository productRepository) {
		Product product1 = new Product();
		product1.setSku(1);
		product1.setName("Teste 1");
		product1.setInventory(new Inventory(new Warehouse("SP", 12, "ECOMMERCE"),
											new Warehouse("SP", 5, "PHYSICAL_STORE")));
		productRepository.insert(product1);

		Product product2 = new Product();
		product2.setSku(2);
		product2.setName("Teste 2");
		product2.setInventory(new Inventory(new Warehouse("SP", 8, "ECOMMERCE"),
											new Warehouse("SP", 10, "PHYSICAL_STORE")));
		productRepository.insert(product2);

		Product product3 = new Product();
		product3.setSku(3);
		product3.setName("Teste 3");
		product3.setInventory(new Inventory(new Warehouse("SP", 7, "ECOMMERCE"),
											new Warehouse("SP", 6, "PHYSICAL_STORE")));
		
		productRepository.insert(product3);
	}

}
