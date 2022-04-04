package br.com.blz.testjava.service.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.blz.testjava.api.v1.model.ProductDTO;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.exception.DuplicateItemException;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.service.IProductService;

@SpringBootTest
class ProductServiceTest {
	@Autowired
	private IProductService productService;
	
	@BeforeEach
	public void before() {
		this.productService.deleteAll();
	}
	
	@Test
	public void shouldThrowsNotFoundExceptionWhenSearchProductWithInvalidSKU() {
		assertThrows(NotFoundException.class, () -> this.productService.findOne(1L));
	}

	@Test
	public void shouldReturnProductWhenSearchBySKU() {
		final Long sku = 1L;
		final ProductDTO product = ProductDTO.builder().sku(sku).build();
		final ProductDTO created = this.productService.create(product);
		final ProductDTO findOne = this.productService.findOne(sku);

		assertEquals(created, findOne);
	}

	@Test
	public void shouldCreateProduct() {
		final Long sku = 1L;
		final ProductDTO product = ProductDTO.builder().sku(sku).build();
		final ProductDTO created = this.productService.create(product);
		
		assertNotNull(created);
	}
	
	@Test
	public void shouldThrowsDuplicateItemExceptionWhenCreateProductWithAnAlreadyExistsSKU() {
		final ProductDTO product = ProductDTO.builder().sku(1L).build();
		this.productService.create(product);
		
		assertThrows(DuplicateItemException.class, () -> this.productService.create(product));
	}

	@Test
	public void shouldUpdateProductBySKU() {
		final Long sku = 1L;
		final ProductDTO newProduct = ProductDTO.builder().sku(sku).name("PRODUTO UM").build();
		this.productService.create(newProduct);
		
		final ProductDTO productToUpdate = ProductDTO.builder().sku(sku).name("PRODUTO UM - ALTERADO").build();
		this.productService.update(sku, productToUpdate);

		final ProductDTO productUpdated = this.productService.findOne(sku);
		assertEquals(productToUpdate.getName(), productUpdated.getName());
	}

	@Test
	public void shouldThrowsNotFoundExecptionWhenUpdateByInexistingSKU() {
		assertThrows(NotFoundException.class, () -> this.productService.update(1L, null));
	}

	@Test
	public void shouldThrowsBusinessExecptionWhenUpdateAProductWithSKUDTODiffSKUKey() {
		final Long sku = 1L;
		final ProductDTO newProduct = ProductDTO.builder().sku(sku).build();
		this.productService.create(newProduct);
		
		final Long difSkuInBody = 9L;
		final ProductDTO productToUpdate = ProductDTO.builder().sku(difSkuInBody).build();
		assertThrows(BusinessException.class, () -> this.productService.update(sku, productToUpdate));
	}
	
	@Test
	public void shouldDeleteProductBySKU() {
		final Long sku = 1L;
		final ProductDTO product = ProductDTO.builder().sku(sku).build();
		this.productService.create(product);
		this.productService.delete(sku);

		assertThrows(NotFoundException.class, () -> this.productService.delete(sku));
	}
	
	@Test
	public void shouldThrowsNotFoundExecptionWhenDeleteByInexistingSKU() {
		assertThrows(NotFoundException.class, () -> this.productService.delete(1L));
	}
}
