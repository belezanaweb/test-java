package br.com.blz.testjava.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
	
	private Long sku;
	private String name;
	private InventoryDTO inventoryDTO;
	private Boolean isMarketable;

	public static ProductDTO from(Product product) {
		
		return ProductDTO.builder()
				.sku(product.getSku())
				.name(product.getName())
				.inventoryDTO(InventoryDTO.from(product.getInventory()))
				.isMarketable(product.getIsMarketable())
				.build();
	}

}
