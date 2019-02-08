package br.com.blz.testjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	
	@InjectMocks
	private ProductService service;
	@Mock
	private ProductRepository repository;

	@Test
	public void shouldCreateProduct() {
		Product p = new Product(1L, "L'Oréal Professionnel");
		service.create(p);
		Mockito.verify(repository,Mockito.times(1)).create(p);
	}
	
	@Test
	public void shouldVerifyProductAlreadyExists() {
		Long sku = 2L;
		Product p = new Product(2L, "L'Oréal Professionnel");
		Mockito.when(repository.get(sku)).thenReturn(p);
		
		exceptionRule.expect(ProductAlreadyExistsException.class);
		exceptionRule.expectMessage("Produto SKU: 2 already exists!");
		
		service.create(p);
	}

	
	@Test
	public void shouldUpdateProduct() {
		Product p = new Product(1L, "L'Oréal Professionnel");
		service.update(p);
		Mockito.verify(repository,Mockito.times(1)).update(p.getSku(),p);
	}

	@Test
	public void shouldGetAllProducts() {
		service.getAll();
		Mockito.verify(repository,Mockito.times(1)).getall();
	}

	
	@Test
	public void shouldGetProduct() {
		Long sku = 1L;
		service.get(sku);
		Mockito.verify(repository,Mockito.times(1)).get(sku);
	}
	
	@Test
	public void shouldDeleteProduct() {
		Long sku = 1L;
		service.delete(sku);
		Mockito.verify(repository,Mockito.times(1)).delete(sku);
	}

	


}
