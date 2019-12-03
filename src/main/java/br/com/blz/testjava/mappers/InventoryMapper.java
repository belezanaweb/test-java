package br.com.blz.testjava.mappers;

import java.util.Objects;
import java.util.stream.Collectors;

import br.com.blz.testjava.mappers.dtos.InventoryDTO;
import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Product;

public class InventoryMapper {
 
	
	public static Inventory toModel(InventoryDTO inventoryDTO, Product product) {
	 
		 final Inventory inventory = Inventory.builder()
				.inventory_id(inventoryDTO.getInventory_id())
				.quantity(inventoryDTO.getQuantity())
				.build();
		
		inventoryDTO.getWarehouses().stream()
			.map(WarehouseMapper::toModel)
			.filter(Objects::nonNull)
			.collect(Collectors.toList())
			.forEach(inventory::addWarehouses);
		
		inventory.setProduct(product);
		
		return inventory;
	}
	

}
