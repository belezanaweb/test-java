package br.com.blz.testjava.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.blz.testjava.entity.InventoryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Singular;

@Data
@Builder
public class InventoryDTO {
	@Setter(AccessLevel.NONE)
	private Long quantity;
	
	@Valid
	@NotNull(message = "Warehouse coud not be null or empty")
	@Singular(value = "addWarehouse")
	private List<WarehouseDTO> warehouses;

	public InventoryEntity toEntity() {
		return InventoryEntity.builder()
				.warehouses(warehouses.stream().map(
							WarehouseDTO::toEntity
						)
				.collect(Collectors.toList()))
				.build();
	}

	public Long getQuantity() {
		return this.warehouses.stream().mapToLong(x -> x.getQuantity()).sum();
	}
}
