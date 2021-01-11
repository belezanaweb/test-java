package br.com.blz.testjava.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.model.Inventory;

class InventoryMapperTest {

	@Test
	void shouldReturnaNewObjectWhenDTOisNull() {
		Inventory inventory = InventoryMapper.toEntity(null);
		assertTrue(inventory.getWhireHouses() == null);
	}
	
	@Test
	void shouldReturnaNewDTOWhenObjectisNull() {
		InventoryDTO inventory = InventoryMapper.toDTO(null);
		assertTrue(inventory.getQuantity() == null);
	}

}
