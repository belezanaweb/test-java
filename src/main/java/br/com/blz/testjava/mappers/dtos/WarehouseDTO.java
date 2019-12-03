package br.com.blz.testjava.mappers.dtos;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class WarehouseDTO {

	private Long warehouse_id;

	@NotEmpty
	private String locality;

	private int quantity;

	@NotEmpty
	private String type;

	private InventoryDTO inventory;

}