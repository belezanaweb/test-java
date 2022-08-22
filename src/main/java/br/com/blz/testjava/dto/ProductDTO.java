package br.com.blz.testjava.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.blz.testjava.entity.ProductEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
	
	@NotNull(message = "SKU coud not be null")
	@Min(value = 1)
	private Long sku;
	
	@NotNull(message = "Name coud not be null")
	private String name;
	
	@Valid
	@NotNull(message = "Inventory coud not be null")
	private InventoryDTO inventory;
	
	public ProductEntity toEntity() {
		return ProductEntity.builder()
				.sku(this.sku)
				.name(this.name)
				.inventory(inventory.toEntity())
				.build();
	}
	
	@JsonProperty("isMarketable")
	public Boolean isMarketable() {
		return this.inventory.getQuantity() > 0;
	}
}
