package br.com.blz.testjava.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.blz.testjava.exceptions.ProductBadRequestException;
import br.com.blz.testjava.exceptions.ProductNotExistsException;
import br.com.blz.testjava.exceptions.ProductSkuAlreadyExistsException;
import br.com.blz.testjava.mappers.ProductMapper;
import br.com.blz.testjava.mappers.dtos.InventoryDTO;
import br.com.blz.testjava.mappers.dtos.ProductDTO;
import br.com.blz.testjava.mappers.dtos.WarehouseDTO;
import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.repostiories.ProductRepository;

public class ProductServiceTest {

	private ProductRepository productRepository;

	private ProductService service;
	
	private Product product;
	
	private ProductDTO productDTO;

	@BeforeEach
	public void setUp() {
		productRepository = Mockito.mock(ProductRepository.class);
		this.service = new ProductService(productRepository);
		
		WarehouseDTO warehouseDTO = WarehouseDTO
				.builder()
				.warehouse_id(4L)
				.locality("local_test")
				.type("type_test")
				.quantity(1)
				.build();
		
		InventoryDTO inventory = InventoryDTO
				.builder()
				.inventory_id(2L)
				.quantity(2)
				.build();
		
		List<WarehouseDTO> listWarehouse = new ArrayList<>();
		listWarehouse.add(warehouseDTO);
		
		inventory.setWarehouses(listWarehouse);
		warehouseDTO.setInventory(inventory);
		
		productDTO = ProductDTO.builder()
				.sku(1L)
				.name("nome_test")
				.marketable(true)
				.inventory(inventory)
				.build();
		
		inventory.setProduct(productDTO);
		
		product = ProductMapper.toModel(productDTO);
	}

	@Test
	public void deveEcontarUmProduct() {
		Warehouse warehouse = new Warehouse();
		
		Inventory inventory = Inventory
				.builder()
				.quantity(5)
				.build();
		inventory.addWarehouses(warehouse);
		
		Product product = new Product();
		product.setName("product name");
		product.setInventory(inventory);
		

		Optional<Product> productOp = Optional.of(product);

		Mockito.when(productRepository.findById(123L)).thenReturn(productOp);

		Product productResult = this.service.search(123L);

		assertEquals("product name", productResult.getName());

	}
	
	@Test
	public void deveLancarUmaProductNotExistsException() {
		
		Mockito.when(productRepository.findById(111L)).thenReturn(Optional.empty());
		
		assertThrows(ProductNotExistsException.class, () -> this.service.search(111L));
	}
	
	@Test
	public void deveLancarProductBadRequestExceptionTentandoAtualizarUmProductComSkuDiferente() {
		assertThrows(ProductBadRequestException.class, () -> this.service.update(111L, new ProductDTO()));
	}
	
	@Test
	public void deveLancarProductNotExistsExceptionTentandoAtualizarUmProductQueNaoExiste() {
		Mockito.when(productRepository.existsById(1L)).thenReturn(false);
		assertThrows(ProductNotExistsException.class, () -> this.service.update(1L, productDTO));
	}
	
	@Test
	public void deveAtualizarUmProductComSuccess() {
		productDTO.setSku(111L);
		
		Mockito.when(productRepository.existsById(111L)).thenReturn(true);
		
		this.service.update(111L, productDTO);
		
		Mockito.verify(productRepository).save(Mockito.any(Product.class));
	}
	
	@Test
	public void deveCriarUmProductComSucesso() {
		
		productDTO.setSku(0L);
		
		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		Mockito.when(productRepository.existsById(0L)).thenReturn(false);
		
		Product productResult = this.service.create(productDTO);
		
		assertEquals("nome_test", productResult.getName());
	}
	
	@Test
	public void deveLancarProductSkuAlreadyExistsExceptionAoTentarSalvarUmProductExistente() {
		
		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		Mockito.when(productRepository.existsById(1L)).thenReturn(true);
		
		assertThrows(ProductSkuAlreadyExistsException.class, () -> this.service.create(productDTO));
	}
	
	@Test
	public void deveLancarProductNotExistsExceptionPassandoSkuNaoExistente() {
		Mockito.when(productRepository.existsById(123L)).thenReturn(false);
		
		assertThrows(ProductNotExistsException.class, () -> this.service.destroy(123L));
	}
	
	@Test
	public void deveExcluirProductComSucesso() {
		Mockito.when(productRepository.existsById(123L)).thenReturn(true);
		
		this.service.destroy(123L);

		Mockito.verify(productRepository, Mockito.times(1)).deleteById(123L);
	}
}
