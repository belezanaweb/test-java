package br.com.blz.testjava.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryDto {
	
	private int id;
	private int quantity;
	private List<WarehouseDto> warehouses;
}
