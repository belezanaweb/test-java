package br.com.blz.testjava.model.dto;

import javax.persistence.CascadeType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
