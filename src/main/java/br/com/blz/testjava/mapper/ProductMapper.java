package br.com.blz.testjava.mapper;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private InventoryMapper inventoryMapper;

    public Product dtoToOrm(ProductDto productDto) {

        Inventory inventory = inventoryMapper.dtoToOrm(productDto.getInventory());

        return Product.builder()
            .sku(productDto.getSku())
            .name(productDto.getName())
            .inventory(inventory)
            .build();

    }

    public ProductDto ormToDto(Product product) {
        InventoryDto inventoryDto = inventoryMapper.ormToDto(product.getInventory());

        return ProductDto.builder()
            .sku(product.getSku())
            .name(product.getName())
            .inventory(inventoryDto)
            .build();

    }

}
