package br.com.blz.testjava.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exception.ProductException;
import br.com.blz.testjava.mock.ProductMock;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	
	@org.junit.Before
	public void setUp() {
		Mockito.when(this.productRepository.findAll()).thenReturn(ProductMock.getProductsDTO());
		Mockito.when(this.productRepository.save(Mockito.any(Product.class))).thenReturn(ProductMock.getNewProduct());
		Mockito.when(this.productRepository.saveAndFlush(Mockito.any(Product.class))).thenReturn(ProductMock.getNewProduct());
	}
	
	
	@Test
	public void getAllProductsTest() {
		List<ProductDTO> products = this.productServiceImpl.getProducts();
		Assert.assertTrue("Expected a list of products", !products.isEmpty());
	}
	
	@Test
	public void getProductBySkuTest() throws ProductException {
		mockProductFindOne();
		ProductDTO product = this.productServiceImpl.getProductBy(43264L);
		Assert.assertNotNull("Expected a Product by sku", product);
	}
	
	@Test(expected = ProductException.class)
	public void createErrorTest() throws ProductException {
		mockProductFindOne();
        final Product newProduct = this.productServiceImpl.create(ProductMock.getNewProductDTO());
		Assert.assertTrue("Expected Error of Business", newProduct.getSku().intValue() == 43265);
	}
	
	@Test
	public void createTest() throws ProductException {
		
        final Product newProduct = this.productServiceImpl.create(ProductMock.getNewProductDTO());
		Assert.assertTrue("Expected a new Product", newProduct.getSku().intValue() == 43265);
	}
	
	
	@Test(expected = ProductException.class)
	public void updateErrorTest() throws ProductException {
		
        final Product newProduct = this.productServiceImpl.update(ProductMock.getNewProductDTO());
		Assert.assertTrue("Expected Error of Business", newProduct.getSku().intValue() == 43265);
	}
	
	@Test
	public void updateTest() throws ProductException {
		mockProductFindOne();
        final Product newProduct = this.productServiceImpl.update(ProductMock.getNewProductDTO());
		Assert.assertTrue("Expected a Update Product", newProduct.getSku().intValue() == 43265);
	}
	
	
	@Test(expected = ProductException.class)
	public void deleteErrorTest() throws ProductException {		
        this.productServiceImpl.delete(43265L);
	}
	
	
	@Test
	public void deleteTest() throws ProductException {	
		mockProductFindOne();
        this.productServiceImpl.delete(43265L);
	}	
	
	public void mockProductFindOne() {
		Mockito.when(this.productRepository.findOne(Mockito.anyLong())).thenReturn(ProductMock.getProductDTO());
	}
	

}
