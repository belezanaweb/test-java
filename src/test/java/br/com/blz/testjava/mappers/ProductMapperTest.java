package br.com.blz.testjava.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.blz.testjava.mappers.dtos.InventoryDTO;
import br.com.blz.testjava.mappers.dtos.ProductDTO;
import br.com.blz.testjava.mappers.dtos.WarehouseDTO;
import br.com.blz.testjava.models.Product;

public class ProductMapperTest {
	
	@Test
	public void deveFazerOParseDeProductDtoParaProductEntity() {
		
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
		
		ProductDTO productDTO = ProductDTO.builder()
				.sku(1L)
				.name("nome_test")
				.marketable(true)
				.inventory(inventory)
				.build();
		
		inventory.setProduct(productDTO);
		
		Product product = ProductMapper.toModel(productDTO);
		
		assertEquals(1, product.getSku());
		assertEquals("nome_test", product.getName());
		assertTrue(product.isMarketable());
		assertEquals(2, product.getInventory().getInventory_id());
		assertEquals(2, product.getInventory().getQuantity());
		assertEquals(product, product.getInventory().getProduct());
		assertEquals(1, product.getInventory().getWarehouses().size());
		assertEquals("local_test", product.getInventory().getWarehouses().get(0).getLocality());
		assertEquals("type_test", product.getInventory().getWarehouses().get(0).getType());
		assertEquals(4, product.getInventory().getWarehouses().get(0).getWarehouse_id());
		assertEquals(2, product.getInventory().getWarehouses().get(0).getInventory().getInventory_id());
	}
}
