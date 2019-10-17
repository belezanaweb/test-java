package br.com.blz.testjava.mapper;

import static java.util.Objects.nonNull;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.dto.ProductDTO;


public class ProductMapper {
	public static Product toEntity(ProductDTO productDto) {
		Product product =  null;
		
		if(nonNull(productDto)) { 
			product = Product.builder()
				.sku(productDto.getSku())
				.name(productDto.getName())
				.build();		
			product.setInventory(InventoryMapper.toEntity(productDto.getInventory(), product));
		}
		
		return product;
		
	}
}
