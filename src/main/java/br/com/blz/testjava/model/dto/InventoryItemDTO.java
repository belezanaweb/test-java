package br.com.blz.testjava.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.blz.testjava.model.enums.StockType;
import lombok.Data;

@Data
public class InventoryItemDTO {

	@NotBlank
	private String locality;
	
	@NotNull
	private Long quantity;
	
	@NotNull
	private StockType type;
}
