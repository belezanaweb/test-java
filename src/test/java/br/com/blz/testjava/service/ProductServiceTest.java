package br.com.blz.testjava.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.Products;
import br.com.blz.testjava.service.exception.ProductAlreadyExistsException;

public class ProductServiceTest {

	private ProductService productService;

	@Mock
	private Products productsMocked;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		productService = new ProductService(productsMocked);
	}

	@Test(expected = ProductAlreadyExistsException.class)
	public void denyCreateProductAlreadyExist() {

		Product productInDataBase = new Product();
		productInDataBase.setId(1L);
		productInDataBase.setSku(47262);
		productInDataBase.setName("Hugo Boss");

		when(productsMocked.findBySku(47262)).thenReturn(Optional.of(productInDataBase));

		Product newProduct = new Product();
		newProduct.setSku(47262);
		newProduct.setName("One Million");

		productService.save(newProduct);
	}

	@Test
	public void createNewProduct() {

		Product productInDataBase = new Product();
		productInDataBase.setId(1L);
		productInDataBase.setSku(47262);
		productInDataBase.setName("One Million");

		Product newProduct = new Product();
		when(productsMocked.save(newProduct)).thenReturn(productInDataBase);
		Product saved = productsMocked.save(newProduct);

		assertThat(saved.getId(), equalTo(1L));
		assertThat(saved.getName(), equalTo("One Million"));
	}
}
