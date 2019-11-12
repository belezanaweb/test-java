package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class InventoryDto implements Serializable {

	private static final long serialVersionUID = 283329507516893903L;

	private int quantity;

	private List<WarehouseDto> warehouses;
	
	public InventoryDto() {}

}
