package br.com.blz.testjava.mappers.dtos;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class InventoryDTO {

	private Long inventory_id;

	private int quantity;

	private ProductDTO product;

	@NotEmpty
	@Valid
	private List<WarehouseDTO> warehouses;

}