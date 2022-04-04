package br.com.blz.testjava.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
	private List<WarehouseDTO> warehouses;
	private Integer quantity;
}