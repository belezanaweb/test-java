package br.com.blz.testjava.business.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;

import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.blz.testjava.business.domain.Product;
import br.com.blz.testjava.business.domain.Warehouse;
import br.com.blz.testjava.business.repository.ProductRepository;
import br.com.blz.testjava.common.exception.BusinessException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class ProdutServiceTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		FixtureFactoryLoader.loadTemplates("br.com.blz");
	}
	
	@Test
	public void shouldSaveProduct() {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		
		Product productCreated = productService.save(product);
		
		assertEquals(1987, productCreated.getSku());
		assertEquals("esmalte", productCreated.getName());
	}
	
	@Test
	public void shouldValidateWhenProductAlreadyExist() {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
		
		try {
			productService.save(product);
			fail("Should throw exception when product already exist");
		} catch (BusinessException e) {
			assertEquals("The product already exist.", e.getErros().get(0).getMessage());
		}
	}
	
	@Test
	public void shouldUpdateProduct() {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		Long sku = 1987l;
		
		productService.update(product, sku);
		
		Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
	}
	
	@Test
	public void shouldValidateWhenProductIdentificationsAreDifferent() {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		Long sku = 1988l;
		
		try {
			productService.update(product, sku);
			fail("Should throw exception when product identifications are different");
		} catch (BusinessException e) {
			assertEquals("The identifications are differents", e.getErros().get(0).getMessage());
		}
	}
	
	@Test
	public void shouldCalculateInventoryQuantity() {
		Warehouse warehouseSp = Fixture.from(Warehouse.class).gimme("valid-warehouse-sp");
		warehouseSp.setQuantity(13);
		
		Warehouse warehouseCuritiba = Fixture.from(Warehouse.class).gimme("valid-warehouse-curitiba");
		warehouseCuritiba.setQuantity(7);
		
		Product product = Fixture.from(Product.class).gimme("valid-product");
		product.getInventory().setWarehouses(Arrays.asList(warehouseSp, warehouseCuritiba));
		
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
		
		Long sku = 1l;
		
		Optional<Product> productExpected = this.productService.findBySku(sku);
		
		assertEquals(20, productExpected.get().getInventory().getQuantity());
	}
	
	@Test
	public void shouldValidateWhenProductIsMarketable() {
		Warehouse warehouseSp = Fixture.from(Warehouse.class).gimme("valid-warehouse-sp");
		warehouseSp.setQuantity(13);
		
		Warehouse warehouseCuritiba = Fixture.from(Warehouse.class).gimme("valid-warehouse-curitiba");
		warehouseCuritiba.setQuantity(7);
		
		Product product = Fixture.from(Product.class).gimme("valid-product");
		product.getInventory().setWarehouses(Arrays.asList(warehouseSp, warehouseCuritiba));
		
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
		
		Long sku = 1l;
		
		Optional<Product> productExpected = this.productService.findBySku(sku);
		
		assertEquals(true, productExpected.get().isMarketable());
	}
	
	@Test
	public void shouldValidateWhenProductIsNotMarketable() {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
		
		Long sku = 1l;
		
		Optional<Product> productExpected = this.productService.findBySku(sku);
		
		assertEquals(false, productExpected.get().isMarketable());
	}
}
