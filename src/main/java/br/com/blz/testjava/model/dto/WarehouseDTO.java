package br.com.blz.testjava.model.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class WarehouseDTO {

	private Integer warehouse_id;

	@NotEmpty
	private String locality;

	private int quantity;

	@NotEmpty
	private String type;
	
    private InventoryDTO inventory;
 
}