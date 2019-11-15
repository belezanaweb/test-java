package br.com.blz.testjava.ServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.blz.testjava.Mother.ProductMother;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.repositories.ProductRepository;
import br.com.blz.testjava.resources.ProductResource;
import br.com.blz.testjava.services.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl service;

	@Mock
	private ProductRepository productRepo;

	@Mock
	private ProductResource proResource;

	@Test
	public void testGetProduct() throws Exception {

		ProductMother mother = new ProductMother();
		Product mock = mother.getProduct();
		Mockito.when(productRepo.findBySku(null)).thenReturn(mock);
		service.findBySku(null);
		Assert.assertEquals("Os valores devem ser iguais", mock, mock);
	}

	@Test
	public void testInsertProduct() throws Exception {

		ProductMother mother = new ProductMother();
		Product mock = mother.getProduct();
		java.util.Optional<Product> i = mother.getProductOptional();
		// Mockito.when(productRepo.findBySku(null)).thenReturn(null);
		service.saveProduct(null);
		Assert.assertEquals("Os valores devem ser iguais", mock, mock);

	}

	@Test
	public void testUpdateProduct() throws Exception {

		ProductMother mother = new ProductMother();
		Product mock = mother.getProduct();
		java.util.Optional<Product> i = mother.getProductOptional();
		// Mockito.when(productRepo.findBySku(null)).thenReturn(null);
		service.updateProduct(mock);
		Assert.assertEquals("Os valores devem ser iguais", mock, mock);

	}

	@Test
	public void testDeleteProduct() throws Exception {

		ProductMother mother = new ProductMother();
		Product mock = mother.getProduct();
		java.util.Optional<Product> i = mother.getProductOptional();
		// Mockito.when(productRepo.findBySku(null)).thenReturn(null);
		service.deleteProduct(null);
		Assert.assertEquals("Os valores devem ser iguais", mock, mock);

	}

}
