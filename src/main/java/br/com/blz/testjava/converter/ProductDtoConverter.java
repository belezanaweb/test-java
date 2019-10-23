package br.com.blz.testjava.converter;

import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDtoConverter implements Converter<Product, ProductDTO> {
    @Autowired private InventoryDtoConverter inventoryConverter;

    @Override
    public ProductDTO perform(final Product input) {
        final ProductDTO result = new ProductDTO();
        result.setSku(input.getSku());
        result.setName(input.getName());
        result.setInventory(inventoryConverter.perform(input.getInventory()));

        return result;
    }
}
