package br.com.blz.testjava.mapper.impl;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.mapper.AbstractMapper;

public class ProductMapper implements AbstractMapper<Product, ProductDto>{

    public ProductMapper() {
        super();
    }

    @Override
    public ProductDto fromEntityToDto(Product entity) {

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setWarehouses(entity.getInventory().getWarehouses());
        long _quantity = entity.getInventory().getWarehouses()
                .stream().mapToLong(Warehouse::getQuantity).sum();
        inventoryDto.setQuantity(_quantity);

        boolean _isMarkable = _quantity>0l;

        return new ProductDto(entity.getSku(), entity.getName(), inventoryDto, _isMarkable);
    }

    @Override
    public Product fromDtoToEntity(ProductDto dto) {

        Inventory inventory = new Inventory();
        inventory.setWarehouses(dto.getInventory().getWarehouses());
        inventory.setQuantity(dto.getInventory().getQuantity());

        return new Product(dto.getSku(), dto.getName(), inventory, dto.isMarketable());
    }

}
