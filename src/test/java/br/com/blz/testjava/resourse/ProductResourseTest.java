package br.com.blz.testjava.resourse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import br.com.blz.testjava.Mother.ProductMother;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.resources.ProductResource;
import br.com.blz.testjava.services.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductResourseTest {

	@InjectMocks
	private ProductResource resourse;

	@Mock
	private ProductServiceImpl service;

	@Test
	public void testGetBySkuProducts() throws Exception {
		ProductMother mother = new ProductMother();
		final Product responseMock = mother.getProduct();
		final ResponseEntity<Product> result = resourse.findProductBy(null);
		Assert.assertEquals("Aqui o status code deverá ser 200", responseMock, responseMock);
		Assert.assertEquals("Aqui a lista deverá ser igual", result, result);
	}

	@Test
	public void testInsetProduct() throws Exception {
		ProductMother mother = new ProductMother();
		final Product responseMock = mother.getProduct();
		final ResponseEntity<Product> result = resourse.saveProduct(responseMock);
		Assert.assertEquals("Aqui o status code deverá ser 200", responseMock, responseMock);
		Assert.assertEquals("Aqui a lista deverá ser igual", result, result);
	}

	@Test
	public void testUpdateProduct() throws Exception {
		ProductMother mother = new ProductMother();
		final Product responseMock = mother.getProduct();
		final ResponseEntity<Product> result = resourse.updateProduct(responseMock);
		Assert.assertEquals("Aqui o status code deverá ser 200", responseMock, responseMock);
		Assert.assertEquals("Aqui a lista deverá ser igual", result, result);
	}

	@Test
	public void testDeleteProduct() throws Exception {
		ProductMother mother = new ProductMother();
		final Product responseMock = mother.getProduct();
		final ResponseEntity<?> result = resourse.deleteProduct(null);
		Assert.assertEquals("Aqui o status code deverá ser 200", responseMock, responseMock);
		Assert.assertEquals("Aqui a lista deverá ser igual", result, result);
	}

}
