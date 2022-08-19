package br.com.blz.testjava.entity;

import br.com.blz.testjava.dto.ProductDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProductEntity {
	@Getter
	private Long sku;
	private String name;
	private InventoryEntity inventory;
	
	
	public ProductDTO toDTO() {
		return ProductDTO.builder()
				.sku(this.sku)
				.name(this.name)
				.inventory(inventory.toDTO())
				.build();
			
	}
}
