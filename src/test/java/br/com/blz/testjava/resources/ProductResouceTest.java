package br.com.blz.testjava.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import br.com.blz.testjava.mappers.ProductMapper;
import br.com.blz.testjava.mappers.dtos.InventoryDTO;
import br.com.blz.testjava.mappers.dtos.ProductDTO;
import br.com.blz.testjava.mappers.dtos.WarehouseDTO;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.services.ProductService;

public class ProductResouceTest {

	private ProductService productService;

	private ProductResource productResource;

	private Product product;

	private ProductDTO productDTO;

	@BeforeEach
	public void setUp() {
		this.productService = Mockito.mock(ProductService.class);
		this.productResource = new ProductResource(productService);

		WarehouseDTO warehouseDTO = WarehouseDTO.builder().warehouse_id(4L).locality("local_test").type("type_test")
				.quantity(1).build();

		InventoryDTO inventory = InventoryDTO.builder().inventory_id(2L).quantity(2).build();

		List<WarehouseDTO> listWarehouse = new ArrayList<>();
		listWarehouse.add(warehouseDTO);

		inventory.setWarehouses(listWarehouse);
		warehouseDTO.setInventory(inventory);

		productDTO = ProductDTO.builder().sku(1L).name("nome_test").marketable(true).inventory(inventory).build();

		inventory.setProduct(productDTO);

		product = ProductMapper.toModel(productDTO);
	}

	@Test
	public void deveCriarUmProductComSucesso() {
		Mockito.when(productService.create(productDTO)).thenReturn(product);

		assertEquals(201, this.productResource.createProduct(productDTO).getStatusCodeValue());
		assertEquals(product, this.productResource.createProduct(productDTO).getBody());
	}

	@Test
	public void deveAutalizarUmProductComSucesso() {

		assertEquals(202, this.productResource.updateProduct(1L, productDTO).getStatusCodeValue());
		Mockito.verify(productService).update(1L, productDTO);
	}
	
	@Test
	public void deveRetornarUmProductComSucesso() {
		Mockito.when(productService.search(123L)).thenReturn(product);
		
		ResponseEntity<Product> searchProductResult = this.productResource.searchProduct(123L);
		
		assertEquals(200, searchProductResult.getStatusCodeValue());
		assertEquals(product, searchProductResult.getBody());
	}
	
	@Test
	public void deveExcluirUmProductComSucesso() {
		
		ResponseEntity<Product> deleteProductResult = this.productResource.deleteProduct(123L);
		
		assertEquals(202, deleteProductResult.getStatusCodeValue());
		
		Mockito.verify(productService, Mockito.times(1)).destroy(123L);
	}
}
