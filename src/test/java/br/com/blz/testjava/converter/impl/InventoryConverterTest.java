package br.com.blz.testjava.converter.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.collect.Sets;

import br.com.blz.testjava.api.v1.model.InventoryDTO;
import br.com.blz.testjava.converter.IInventoryConverter;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Warehouse;

@SpringBootTest
class InventoryConverterTest {
	@Autowired
	private IInventoryConverter inventoryConverter;
	
	@Test
	void shouldCalculateQuantity() {
		final Inventory inventory = Inventory.builder()
				.warehouses(Sets.newHashSet(
						Warehouse.builder().quantity(10).build(),
						Warehouse.builder().quantity(5).build()
				))
				.build();
		
		final InventoryDTO dto = this.inventoryConverter.toDTO(inventory);
		assertEquals(15, dto.getQuantity());
	}
	
	@Test
	void shouldCalculateQuantityWhenHaveAWarehouseWithNullQuantity() {
		final Inventory inventory = Inventory.builder()
				.warehouses(Sets.newHashSet(
						Warehouse.builder().build(),
						Warehouse.builder().quantity(5).build()
				))
				.build();
		
		final InventoryDTO dto = this.inventoryConverter.toDTO(inventory);
		assertEquals(5, dto.getQuantity());
	}
	
	@Test
	void shouldCalculateQuantityWhenWarehousesAreEmpty() {
		final Inventory inventory = Inventory.builder().build();
		
		final InventoryDTO dto = this.inventoryConverter.toDTO(inventory);
		assertEquals(0, dto.getQuantity());
	}
}