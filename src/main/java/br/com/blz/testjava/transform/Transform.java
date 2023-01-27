package br.com.blz.testjava.transform;

import java.util.List;
import java.util.stream.Collectors;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.repository.entity.Inventory;
import br.com.blz.testjava.repository.entity.Product;
import br.com.blz.testjava.repository.entity.Warehouse;

public class Transform {

	public static Product toProduct(ProductDto dto) {
		if (dto == null) {
			return null;
		}

		Product product = new Product();
		product.setSku(dto.getSku());
		product.setName(dto.getName());
		product.setInventory(toInventory(dto.getInventory()));

		return product;
	}

	public static Inventory toInventory(InventoryDto dto) {
		if (dto == null) {
			return null;
		}

		Inventory inventory = new Inventory();

		List<WarehouseDto> warehousesDto = dto.getWarehouses();
		if (warehousesDto != null) {
			inventory.setWarehouses(
					warehousesDto.stream()
							.map(wDto -> toWarehouse(wDto))
							.collect(Collectors.toList()));
		}

		return inventory;
	}

	public static Warehouse toWarehouse(WarehouseDto dto) {
		if (dto == null) {
			return null;
		}

		Warehouse warehouse = new Warehouse();
		warehouse.setLocality(dto.getLocality());
		warehouse.setQuantity(dto.getQuantity());
		warehouse.setType(dto.getType());

		return warehouse;
	}

	public static ProductDto toProductDto(Product entity) {
		if (entity == null) {
			return null;
		}

		ProductDto productDto = new ProductDto();
		productDto.setSku(entity.getSku());
		productDto.setName(entity.getName());
		productDto.setInventory(toInventoryDto(entity.getInventory()));

		return productDto;
	}

	public static InventoryDto toInventoryDto(Inventory entity) {
		if (entity == null) {
			return null;
		}

		InventoryDto inventoryDto = new InventoryDto();

		List<Warehouse> warehouses = entity.getWarehouses();
		if (warehouses != null) {
			inventoryDto.setWarehouses(
					warehouses.stream()
							.map(w -> toWarehouseDto(w))
							.collect(Collectors.toList()));
		}

		return inventoryDto;
	}

	public static WarehouseDto toWarehouseDto(Warehouse entity) {
		if (entity == null) {
			return null;
		}

		WarehouseDto warehouseDto = new WarehouseDto();
		warehouseDto.setLocality(entity.getLocality());
		warehouseDto.setQuantity(entity.getQuantity());
		warehouseDto.setType(entity.getType());

		return warehouseDto;
	}

}
