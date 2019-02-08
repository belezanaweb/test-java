package br.com.blz.testjava.repository;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.blz.testjava.model.Invetory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryImplTest {

	@InjectMocks
	private ProductRepositoryImpl repository;

	@Test
	public void shouldGetAllProducts() {
		int sizeExpected = 2;
		
		Set<Warehouse> warehouses = Sets.newSet(new Warehouse("SP", 2, "ECOMMERCE"),new Warehouse("MOEMA", 3, "PHYSICAL_STORE"));
		Invetory invetory = new Invetory(5, warehouses);
		Product product = new Product(1L, "L'Oréal Professionnel", invetory);

		Set<Warehouse> warehouses2 = Sets.newSet(new Warehouse("RJ", 1, "ECOMMERCE"));
		Invetory invetory2 = new Invetory(1, warehouses2);
		Product product2 = new Product(2L, "Dove mem care", invetory2);
		
		repository.create(product);
		repository.create(product2);

		Collection<Product> productsFromDB = repository.getall();

		assertEquals(sizeExpected, productsFromDB.size());
	}

	
	@Test
	public void shouldCreateProduct() {
		Long sku = 43264L;
		Set<Warehouse> warehouses = Sets.newSet(new Warehouse("SP", 2, "ECOMMERCE"),new Warehouse("MOEMA", 3, "PHYSICAL_STORE"));
		Invetory invetory = new Invetory(5, warehouses);
		Product product = new Product(sku, "L'Oréal Professionnel", invetory);

		repository.create(product);

		Product productFromDB = repository.get(sku);

		assertEquals(product, productFromDB);
	}

	@Test
	public void shouldUpdateProduct() {
		Long sku = 43264L;
		String updatedName = "Dove men care";
		Product product = new Product(sku, "L'Oréal Professionnel");
		repository.create(product);

		product.setName(updatedName);
		repository.update(sku, product);

		Product productFromDB = repository.get(sku);

		assertEquals(updatedName, productFromDB.getName());
	}

	@Test
	public void shouldDeleteProduct() {
		Long sku = 43264L;
		Product product = new Product(sku, "L'Oréal Professionnel");
		repository.create(product);
		
		repository.delete(sku);
		
		assertEquals(null, repository.get(sku));
	}

}
