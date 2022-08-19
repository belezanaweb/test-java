package br.com.blz.testjava.entity;

import java.util.List;
import java.util.stream.Collectors;

import br.com.blz.testjava.dto.InventoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity {
	@Getter
	private List<WarehouseEntity> warehouses;

	public InventoryDTO toDTO() {
		return InventoryDTO.builder()
				.warehouses(warehouses.stream().map(
						WarehouseEntity::toDTO
						)
				.collect(Collectors.toList()))
				.build();
	}
	
	
}
