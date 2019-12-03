package br.com.blz.testjava.mappers;

import br.com.blz.testjava.mappers.dtos.ProductDTO;
import br.com.blz.testjava.models.Product;

public class ProductMapper {
	public static Product toModel(ProductDTO productDTO) {
		
		Product product = Product.builder()
			.sku(productDTO.getSku())
			.name(productDTO.getName())
			.marketable(productDTO.isMarketable())
			.build();
		
		product.setInventory(InventoryMapper.toModel(productDTO.getInventory(), product));
				
		return product;
		
	}
}
