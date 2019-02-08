package br.com.blz.testjava.model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

public class ProductTest {

	@Test
	public void shouldSetMarketableToTrueWhenInvetoryQuantityIsBiggerThanZero() {
		Long sku = 43264L;
		Set<Warehouse> warehouses = Sets.newSet(new Warehouse("SP", 2, "ECOMMERCE"),new Warehouse("MOEMA", 3, "PHYSICAL_STORE"));
		Invetory invetory = new Invetory(5, warehouses);
		Product product = new Product(sku, "L'Oréal Professionnel", invetory); 

		assertTrue(product.isMarketable());
	}
	
	@Test
	public void shouldSetMarketableToFalseWhenInvetoryQuantityIsEqualsOrSmallerThanZero() {
		Long sku = 43264L;
		Product product = new Product(sku, "L'Oréal Professionnel", null); 

		assertFalse(product.isMarketable());
	}

}
