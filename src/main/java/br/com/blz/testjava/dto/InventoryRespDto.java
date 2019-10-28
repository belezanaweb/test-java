
package br.com.blz.testjava.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryRespDto  {
    @JsonProperty("warehouses")
    private List<WarehouseRespDto> warehouses;
    private Integer quantity;

	public List<WarehouseRespDto> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseRespDto> warehouses) {
		this.warehouses = warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


}
