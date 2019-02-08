package br.com.blz.testjava.model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

public class InvetoryTest {

	@Test
	public void shouldCalculateInvetoryQuantity() {
		int expectedQuantity = 13;
		Set<Warehouse> warehouses = Sets.newSet(new Warehouse("SP", 4, "ECOMMERCE"),new Warehouse("MOEMA", 9, "PHYSICAL_STORE"));
		Invetory invetory = new Invetory(5,warehouses);
		
		assertEquals(expectedQuantity, invetory.getQuantity());		
	}
	
	@Test
	public void shouldCalculateInvetoryQuantityWhenWarehousesAreNull() {
		int expectedQuantity = 0;
		Invetory invetory = new Invetory(0,null);
		
		assertEquals(expectedQuantity, invetory.getQuantity());		
	}

}
