package br.com.blz.testjava.mapper;

import java.math.BigDecimal;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.model.Inventory;

public class InventoryMapper {
	
	private InventoryMapper() {}
	
	public static Inventory toEntity(InventoryDTO inventoryDTO) {
		
		
		Inventory inventory = new Inventory();
		if(inventoryDTO == null) {
			return inventory;
		}
		inventory.setWhireHouses(WareHousesMapper.toEntities(inventoryDTO.getWarehouses()));
		
		return inventory;
	}

	public static InventoryDTO toDTO(Inventory inventory) {
		
		InventoryDTO dto = new InventoryDTO();
		if(inventory == null) {
			return dto;
		}
		dto.setWarehouses(WareHousesMapper.toDTOS(inventory.getWhireHouses()));
		dto.setQuantity(inventory.getWhireHouses().stream()
				.map(w -> w.getQuantity())
				.reduce(BigDecimal.ZERO, BigDecimal::add));
		
		return dto;
	}

}
