package br.com.blz.testjava.mapper;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.dto.InventoryDTO;

public class InventoryMapper {
 
	
	public static Inventory toEntity(InventoryDTO inventoryDTO, Product product) {
		Inventory inventory = null; 
		inventory = Inventory.builder()
				.quantity(inventoryDTO.getQuantity())
				.product(product)
				.build();
		
		WarehouseMapper.toEntities(inventoryDTO.getWarehouses()).forEach(inventory::addWarehouses);
		
		return inventory;
	}
	

}
