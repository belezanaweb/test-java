package br.com.blz.testjava.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InventoryDTO {
	
	public int quantity;
	public List<WarehousesDTO> warehouses;
	
}
