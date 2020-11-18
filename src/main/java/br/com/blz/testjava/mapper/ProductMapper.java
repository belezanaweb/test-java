package br.com.blz.testjava.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.blz.testjava.business.domain.Inventory;
import br.com.blz.testjava.business.domain.Product;
import br.com.blz.testjava.business.domain.Warehouse;
import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;

public class ProductMapper {
	
	public static ProductDto toDto(Product product) {
		List<WarehouseDto> warehouses = Optional.ofNullable(product.getInventory().getWarehouses()).orElse(Collections.emptyList()).stream().map(warehouse -> {
			return WarehouseDto.builder()
					.locality(warehouse.getLocality())
					.quantity(warehouse.getQuantity())
					.type(warehouse.getType())
					.build();
		}).collect(Collectors.toList());
		
		return ProductDto.builder()
				.sku(product.getSku())
				.name(product.getName())
				.isMarketable(product.isMarketable())
				.inventory(InventoryDto.builder()
						.quantity(product.getInventory().getQuantity())
						.warehouses(warehouses)
						.build())
				.build();
	}
	
	public static Product toEntity(ProductDto productDto) {
		List<Warehouse> warehouses = productDto.getInventory().getWarehouses().stream().map(warehouse -> {
			return Warehouse.builder()
					.locality(warehouse.getLocality())
					.quantity(warehouse.getQuantity())
					.type(warehouse.getType())
					.build();
		}).collect(Collectors.toList());
		
		return Product.builder()
				.sku(productDto.getSku())
				.name(productDto.getName())
				.inventory(Inventory.builder()
						.warehouses(warehouses)
						.build())
				.build();
	}
}
