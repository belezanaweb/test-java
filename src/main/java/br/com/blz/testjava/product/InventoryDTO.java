package br.com.blz.testjava.product;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDTO {
	
	private Long quantity;
	private List<WarehouseDTO> warehousesDTO;
	
	public static InventoryDTO from(Inventory inventory) {
		
		return InventoryDTO.builder()
				.quantity(inventory.getQuantity())
				.warehousesDTO(inventory.getWarehouses().stream().map(WarehouseDTO::from).collect(Collectors.toList()))
				.build();
	}

}
