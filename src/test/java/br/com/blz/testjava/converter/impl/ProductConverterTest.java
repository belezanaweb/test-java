package br.com.blz.testjava.converter.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.collect.Sets;

import br.com.blz.testjava.api.v1.model.ProductDTO;
import br.com.blz.testjava.converter.IProductConverter;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;

@SpringBootTest
class ProductConverterTest {
	@Autowired
	private IProductConverter productConverter;
	
	@Test
	void shouldDefineMarketableTrueWhenQuantityIsMoreThanZero() {
		final Product product = Product.builder()
				.inventory(Inventory.builder()
					.warehouses(Sets.newHashSet(
							Warehouse.builder().quantity(10).build(),
							Warehouse.builder().quantity(5).build()
					)).build())
				.build();
		
		final ProductDTO dto = this.productConverter.toDTO(product);
		assertTrue(dto.isMarketable());
	}
	
	@Test
	void shouldDefineMarketableFalseWhenQuantityIsZero() {
		final Product product = Product.builder()
				.inventory(Inventory.builder()
					.warehouses(Sets.newHashSet(
							Warehouse.builder().quantity(0).build(),
							Warehouse.builder().quantity(0).build()
					)).build())
				.build();
		
		final ProductDTO dto = this.productConverter.toDTO(product);
		assertFalse(dto.isMarketable());
	}
	
	@Test
	void shouldDefineMarketableFalseWhenWarehousesAreEmpty() {
		final Product product = Product.builder().build();
		
		final ProductDTO dto = this.productConverter.toDTO(product);
		assertFalse(dto.isMarketable());
	}
}