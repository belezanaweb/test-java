
package br.com.blz.testjava.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDto  {
    @JsonProperty("warehouses")
    private List<WarehouseDto> warehouses;

	public List<WarehouseDto> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseDto> warehouses) {
		this.warehouses = warehouses;
	}


}
