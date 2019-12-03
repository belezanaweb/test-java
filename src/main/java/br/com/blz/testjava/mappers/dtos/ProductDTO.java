package br.com.blz.testjava.mappers.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ProductDTO {
 
	@NotNull
	private Long sku;
	
	@NotEmpty
	private String name;
	
	@JsonProperty("isMarketable")
	private boolean marketable;
	
	@NotNull
	@Valid 
	private InventoryDTO inventory;
 
}