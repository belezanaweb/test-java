package br.com.blz.testjava.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.model.InventoryItem;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.enums.StockType;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;
	
	private static final String SKU_DEFAULT = "SKU-0001";

	@Test
	@Order(1)
	void testAddSuccess() {
		Product product = this.getProductDefault();
		
		product = this.repository.save(product);
		
		log.debug("product inserted {}", product);
		
		assertTrue(this.repository.existsById(SKU_DEFAULT));
		
	}
	
	
	@Test
	@Order(2)
	void testAddError() {
		
		Product product = this.getProductDefault();
		
		InventoryItem item = new InventoryItem(); item.setLocality("RJ");;
		item.setQuantity(300L); item.setType(StockType.ECOMMERCE);
		product.getInventory().add(item);
		
		assertThrows(DataIntegrityViolationException.class, () ->{ 			
			this.repository.save(product);
		});
		
	}
	
	@Test
	@Order(1)
	void testUpdateSuccess() {
		Product product = this.getProductDefault();
		product.getInventory().get(0).setQuantity(0L);
		product.getInventory().get(1).setQuantity(15L);
		
		product = this.repository.save(product);
		
		log.debug("product updated {}", product);
		
		assertThat(product.getInventory(), containsInAnyOrder(
                hasProperty("quantity", is(0L)),
                hasProperty("quantity", is(15L))
        ));
		
		product.getInventory().remove(0);
		
		product = this.repository.save(product);
		
		product = this.repository.findById(SKU_DEFAULT).orElse(null);
		
		assertThat(product.getInventory(), containsInAnyOrder(
                hasProperty("quantity", is(15L))
        ));
		assertThat("update not effective", product.getInventory().size() == 1);
	}
	
	@Test
	@Order(3)
	void testQuery() {
		Optional<Product> optProduct = this.repository.findById(SKU_DEFAULT);
			
		assertAll("Fail on query Product",  
				() -> assertTrue(optProduct.isPresent()),
				() -> assertThat(optProduct.get().getName(), notNullValue()),
				() -> assertThat(optProduct.get().getInventory(), hasSize(2))
			);
		
	}
	
	@Test
	@Order(4)
	void testDelete() {
		assertTrue(this.repository.existsById(SKU_DEFAULT));
		
		this.repository.deleteById(SKU_DEFAULT);
		
		assertFalse(this.repository.existsById(SKU_DEFAULT));
		
	}
	
	private Product getProductDefault() {
		Product product = new Product();
		product.setSKU(SKU_DEFAULT);
		product.setName("first product");
		
		List<InventoryItem> items = new ArrayList<>();
		
		InventoryItem item = new InventoryItem();
		
		item.setLocality("sp");
		item.setQuantity(10L);
		item.setType(StockType.PHYSICAL_STORE);
		items.add(item);
		
		item = new InventoryItem();
		item.setLocality("RJ");;
		item.setQuantity(300L);
		item.setType(StockType.ECOMMERCE);
		items.add(item);
		
		product.setInventory(items);
		
		return product;
		
	}
	
	

}
