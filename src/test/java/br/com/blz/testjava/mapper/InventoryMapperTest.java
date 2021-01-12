package br.com.blz.testjava.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.WareHouseDTO;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.WareHouse;

class InventoryMapperTest {

	private static final String SUM = "10";
	private static final String FIRST_ELEMENT = "0";

	@Test
	void shouldReturnaNewObjectWhenDTOisNull() {
		Inventory inventory = InventoryMapper.toEntity(null);
		assertTrue(inventory.getWareHouses() == null);
	}
	
	@Test
	void shouldReturnaNewDTOWhenObjectIsNull() {
		InventoryDTO inventory = InventoryMapper.toDTO(null);
		assertTrue(inventory.getQuantity() == null);
	}
	
	@Test
	void shouldReturnaDTOWhenObjectIsNotNull() {
		Inventory entity = new Inventory();
		Vector<WareHouse> warehouses= criateWarehouses();
		entity.setWareHouses(warehouses);
		InventoryDTO inventory = InventoryMapper.toDTO(entity);
		assertTrue(inventory.getQuantity().equals(new BigDecimal(SUM)));
	}
	
	@Test
	void shouldReturnaObjectWhenDTOisNotNull() {
		InventoryDTO dto = new InventoryDTO();
		Vector<WareHouseDTO> warehouses= criateWarehousesDTO();
		dto.setWarehouses(warehouses);
		Inventory inventory = InventoryMapper.toEntity(dto);
		assertTrue(inventory.getWareHouses().get(0).getQuantity().equals(new BigDecimal(FIRST_ELEMENT)));
	}

	private Vector<WareHouseDTO> criateWarehousesDTO() {
		Vector<WareHouseDTO> warehouses = new Vector<>();
		for(int i = 0; i< 5; i++) {
			WareHouseDTO warehouse = new WareHouseDTO();
			warehouse.setQuantity(new BigDecimal(i));
			warehouses.add(warehouse);
		}
		return warehouses;
	}

	private Vector<WareHouse> criateWarehouses() {
		Vector<WareHouse> warehouses = new Vector<>();
		for(int i = 0; i< 5; i++) {
			WareHouse warehouse = new WareHouse();
			warehouse.setQuantity(new BigDecimal(i));
			warehouses.add(warehouse);
		}
		return warehouses;
	}
	

}
