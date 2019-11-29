package br.com.blz.testjava.product;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value="Inventory", description="Classe que representa o estoque")
public class InventoryDTO {
	
	@ApiModelProperty(value="Quantidade total em estoque")
	private Long quantity;
	private List<WarehouseDTO> warehousesDTO;
	
	public static InventoryDTO from(Inventory inventory) {
		
		return InventoryDTO.builder()
				.quantity(inventory.getQuantity())
				.warehousesDTO(inventory.getWarehouses().stream().map(WarehouseDTO::from).collect(Collectors.toList()))
				.build();
	}

	public Inventory parse() {
		return Inventory.builder()
				.quantity(this.quantity)
				.warehouses(warehousesDTO.stream().map(WarehouseDTO::parse).collect(Collectors.toList()))
				.build();
	}

}
