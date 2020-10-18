package br.com.blz.testjava.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;

import br.com.blz.testjava.model.view.ProductViews.ProductRead;
import br.com.blz.testjava.model.view.ProductViews.ProductWrite;
import lombok.Data;

@Data
public class ProductDTO {

	@JsonView(ProductWrite.class)
	@JsonProperty("sku")
	@NotBlank
	private String SKU;
	
	@JsonView(ProductWrite.class)
	@NotBlank
	private String name;	
	
	@JsonView(ProductWrite.class)
	@JsonProperty("inventory")
	@Valid
	@NotNull
    private InventoryItemsDTO inventory;
	
	@JsonView(ProductRead.class)
	@JsonProperty("isMarketable")
	private boolean isMarketable;

}
