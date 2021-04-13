package dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryDTO {
	
	@JsonProperty("warehouses")
	private List<WarehouseDTO> warehousesDTO;

	public InventoryDTO() {

	}

	public InventoryDTO(List<WarehouseDTO> dto) {
		this.warehousesDTO = dto;
	}

	
	
	public int getQuantity() {
		return warehousesDTO.stream().mapToInt(warehouseto -> warehouseto.getQuantity()).sum();
	}

	public List<WarehouseDTO> getWarehousesDTO() {
		return warehousesDTO;
	}

	public void setWarehousesDTO(List<WarehouseDTO> warehousesDTO) {
		this.warehousesDTO = warehousesDTO;
	}


}
