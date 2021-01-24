package br.com.blz.testjava.repository;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTests {

	@Autowired
	private IProductRepository repository;

	@Test
	public void saveTest() {

		Product prod = new Product();
		prod.setSku(1L);
		prod.setName("Prod teste 1");
		Product prodResul = repository.save(prod);
		assertEquals(prodResul.getSku(), 1L);

		Product prod2 = new Product();
		prod2.setSku(1L);
		prod2.setName("Prod teste 1 alterado");
		Product prodResul2 = repository.save(prod2);
		assertEquals(prodResul2.getName(), "Prod teste 1 alterado");

	}

	@Test
	public void findByIdTest() {

		Product prod = new Product();
		prod.setSku(1L);
		prod.setName("Prod teste 1");
		repository.save(prod);

		Product found = repository.findById(1L).orElse(null);
		assertNotNull(found);
		assertEquals(found.getSku(), 1L);

		Product found2 = repository.findById(2L).orElse(null);
		assertNull(found2);

	}
	@Test
	public void existsByIdTest() {
		Product prod = new Product();
		prod.setSku(1L);
		prod.setName("Prod teste 1");
		repository.save(prod);

		assertTrue(repository.existsById(1L));
		assertFalse(repository.existsById(2L));
	}

	@Test
	public void deleteByIdTest() {
		Product prod = new Product();
		prod.setSku(1L);
		prod.setName("Prod teste 1");
		 repository.save(prod);

		Product prod2 = new Product();
		prod2.setSku(2L);
		prod2.setName("Prod teste 2");
		repository.save(prod2);
		
		assertTrue(repository.existsById(1L));
		assertTrue(repository.existsById(2L));
		
		repository.deleteById(2L);
		assertTrue(repository.existsById(1L));
		assertFalse(repository.existsById(2L));
		
		
		
	}

}
