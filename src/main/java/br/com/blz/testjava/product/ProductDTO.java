package br.com.blz.testjava.product;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
	
	@NotNull
	private Long sku;
	@NotNull
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
	
	public Product parse() {
		
		return Product.builder()
				.sku(this.sku)
				.name(this.name)
				.inventory(this.inventoryDTO.parse())
				.isMarketable(this.isMarketable)
				.build();
	}

}
