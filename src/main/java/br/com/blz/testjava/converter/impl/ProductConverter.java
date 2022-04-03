package br.com.blz.testjava.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.api.v1.model.InventoryDTO;
import br.com.blz.testjava.api.v1.model.ProductDTO;
import br.com.blz.testjava.converter.IInventoryConverter;
import br.com.blz.testjava.converter.IProductConverter;
import br.com.blz.testjava.domain.Product;

@Service
public class ProductConverter implements IProductConverter {

	@Autowired
	private IInventoryConverter inventoryConverter;
	
	@Override
	public Product toDomain(final ProductDTO dto) {
		return Product.builder()
				.sku(dto.getSku())
				.name(dto.getName())
				.inventory(this.inventoryConverter.toDomain(dto.getInventory()))
				.build();
	}

	@Override
	public ProductDTO toDTO(final Product domain) {
		final InventoryDTO inventoryDTO = this.inventoryConverter.toDTO(domain.getInventory());
		
		return ProductDTO.builder()
				.sku(domain.getSku())
				.name(domain.getName())
				.inventory(inventoryDTO)
				.marketable(inventoryDTO.getQuantity() > 0)
				.build();
	}
}
