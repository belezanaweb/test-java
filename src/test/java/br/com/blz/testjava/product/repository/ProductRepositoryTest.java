package br.com.blz.testjava.product.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.blz.testjava.product.model.Inventory;
import br.com.blz.testjava.product.model.Product;
import br.com.blz.testjava.product.model.Warehouse;
import br.com.blz.testjava.product.model.WarehouseType;

@DataJpaTest
class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;
	
	private Product p;
	
	@BeforeEach
	public void setUp() {
		Warehouse warehouseSP = Warehouse.builder().locality("SP").quantity(12L).type(WarehouseType.ECOMMERCE).build();
		Warehouse warehouseMoema = Warehouse.builder().locality("MOEMA").quantity(3L).type(WarehouseType.PHYSICAL_STORE).build();
		Inventory inventory = Inventory.builder().warehouses(Arrays.asList(warehouseSP,warehouseMoema)).build();
		
		 p = Product.builder().name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g").sku(43264L).inventory(inventory).build();
		
		 repository.save(p);
		
	}
	
	@Test
	void shouldFindProduct_bySKU() {
		assertNotNull(repository.findBySku(p.getSku()));
	}
	
	@Test
	void productMustBeMarketable() {
		Product product = repository.findBySku(p.getSku());
		
		assertNotNull(product);
		
		assertTrue(product.isMarketable());
	}
	
	@Test
	void productQuantityMustBe15() {
		Product product = repository.findBySku(p.getSku());
		
		assertNotNull(product);
		
		assertEquals(product.getInventory().getQuantity(), 15L);
		
	}
	
	@Test
	void mustNotLetPersistTwoProductWithSameSKU() {
		Warehouse warehouseSP = Warehouse.builder().locality("SP").quantity(12L).type(WarehouseType.ECOMMERCE).build();
		Inventory inventory = Inventory.builder().warehouses(Arrays.asList(warehouseSP)).build();
		
		Product p = Product.builder().name("L'Oréal 2").sku(43264L).inventory(inventory).build();
		
		//Faz o assert em data integrity pois o throw de product exception é só na camada de component, aqui é feita a validação pelo unique = true do JPA
		assertThrows(DataIntegrityViolationException.class, () -> repository.save(p));
		
	}

	
	@Test
	void productWithNoInventoryShouldNotBeMarketable() {

		Product productNotMarketable = Product.builder().name("L'Oréal 2").sku(1L).build();
		repository.save(productNotMarketable);
		
		Product product = repository.findBySku(1L);
		
		assertFalse(product.isMarketable());
		
	}
	
	
	@Test
	void productWithNoQuantityInExistingInventoryShouldNotBeMarketable() {

		Warehouse warehouseSP = Warehouse.builder().locality("SP").quantity(0L).type(WarehouseType.ECOMMERCE).build();
		Inventory inventory = Inventory.builder().warehouses(Arrays.asList(warehouseSP)).build();
		Product productNotMarketable = Product.builder().name("L'Oréal 2").inventory(inventory).sku(1L).build();
		repository.save(productNotMarketable);
		
		Product product = repository.findBySku(1L);
		
		assertFalse(product.isMarketable());
	}
	
	@Test
	void mustDeleteProductBySKU() {
		
		repository.deleteBySku(p.getSku());
		
		Product product = repository.findBySku(p.getSku());
		
		assertNull(product);
		
	}
	
	
}
