package br.com.blz.testjava.product;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value="Product", description="Classe que representa um produto")
public class ProductDTO {
	
	@NotNull
	@ApiModelProperty(value="Código SKU do produto")
	private Long sku;
	@NotNull
	@ApiModelProperty(value="Código nome do produto")
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
