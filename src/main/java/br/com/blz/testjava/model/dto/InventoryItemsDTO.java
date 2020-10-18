package br.com.blz.testjava.model.dto;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.blz.testjava.model.view.ProductViews.ProductRead;
import br.com.blz.testjava.model.view.ProductViews.ProductWrite;
import lombok.Data;

@Data
public class InventoryItemsDTO {
	
	@JsonView(ProductRead.class)
	private Long quantity;
	
	@JsonProperty("warehouses")
	@JsonView(ProductWrite.class)
	@Valid
	private List<InventoryItemDTO> items;

}
