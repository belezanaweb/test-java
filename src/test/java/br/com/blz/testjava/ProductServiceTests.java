package br.com.blz.testjava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.blz.testjava.data.dto.InventoryDTO;
import br.com.blz.testjava.data.dto.ProductDTO;
import br.com.blz.testjava.data.dto.WarehouseDTO;
import br.com.blz.testjava.data.repositories.ProductRepositoryImpl;
import br.com.blz.testjava.data.services.ProductService;
import br.com.blz.testjava.domain.entities.Inventory;
import br.com.blz.testjava.domain.entities.Product;
import br.com.blz.testjava.domain.entities.Warehouse;



public class ProductServiceTests {

	private ProductRepositoryImpl repository = new ProductRepositoryImpl();

	private ModelMapper mapper = new ModelMapper();
	
	private ProductService service = new ProductService(repository, mapper);

	public ProductServiceTests() {
		
	}
	
	@Test
	public void testAddProduct() throws Exception {
		WarehouseDTO warehouse1 = new WarehouseDTO("SP", 10, "ECOMMERCE");
		WarehouseDTO warehouse2 = new WarehouseDTO("MOEMA", 1, "LOJA FISICA");
		List<WarehouseDTO> warehouses = new ArrayList<WarehouseDTO>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setWarehouses(warehouses);
		
		ProductDTO productDTO = new ProductDTO("999999", "Produto de testes", inventoryDTO, true);
		
		String sku = this.service.add(productDTO);
		
		assertEquals(sku, "999999");
	}
	
	@Test
	public void testAddProductDuplicated() throws Exception {
		WarehouseDTO warehouse1 = new WarehouseDTO("SP", 10, "ECOMMERCE");
		WarehouseDTO warehouse2 = new WarehouseDTO("MOEMA", 1, "LOJA FISICA");
		List<WarehouseDTO> warehouses = new ArrayList<WarehouseDTO>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setWarehouses(warehouses);
		
		ProductDTO productDTO = new ProductDTO("999999", "Produto de testes", inventoryDTO, true);
		
		String sku = this.service.add(productDTO);
		
		Exception exception = assertThrows(Exception.class, () -> {
	        this.service.add(productDTO);
	    });

	    String expectedMessage = "This product already exists";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testDeleteProductTrue() throws Exception {
		WarehouseDTO warehouse1 = new WarehouseDTO("SP", 10, "ECOMMERCE");
		WarehouseDTO warehouse2 = new WarehouseDTO("MOEMA", 1, "LOJA FISICA");
		List<WarehouseDTO> warehouses = new ArrayList<WarehouseDTO>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setWarehouses(warehouses);
		
		ProductDTO productDTO = new ProductDTO("999999", "Produto de testes", inventoryDTO, true);
		String skuInserted = this.service.add(productDTO);
		
		Boolean isDeleted = this.service.delete(skuInserted);
		
		assertEquals(isDeleted, true);
		
	}
	
	@Test
	public void testDeleteProductFalse() {
		Boolean isDeleted = this.service.delete("888888");
		assertEquals(isDeleted, false);
	}
	
	@Test
	public void testFindProductBySku() throws Exception {
		WarehouseDTO warehouse1 = new WarehouseDTO("SP", 10, "ECOMMERCE");
		WarehouseDTO warehouse2 = new WarehouseDTO("MOEMA", 1, "LOJA FISICA");
		List<WarehouseDTO> warehouses = new ArrayList<WarehouseDTO>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setWarehouses(warehouses);
		
		ProductDTO productDTO = new ProductDTO("999999", "Produto de testes", inventoryDTO, true);
		String skuInserted = this.service.add(productDTO);
		
		ProductDTO productFound = this.service.find(skuInserted);
		
		assertEquals(productFound.getName(), "Produto de testes");
		assertEquals(productFound.getSku(), "999999");
		
	}
	
	@Test
	public void testFindProductBySkuFalse() throws Exception {
		WarehouseDTO warehouse1 = new WarehouseDTO("SP", 10, "ECOMMERCE");
		WarehouseDTO warehouse2 = new WarehouseDTO("MOEMA", 1, "LOJA FISICA");
		List<WarehouseDTO> warehouses = new ArrayList<WarehouseDTO>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setWarehouses(warehouses);
		
		ProductDTO productDTO = new ProductDTO("999999", "Produto de testes", inventoryDTO, true);
		this.service.add(productDTO);
		
		ProductDTO productFound = this.service.find("8888888");
		
		assertEquals(productFound, null);
		
	}
	
}
