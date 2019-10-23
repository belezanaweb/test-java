package br.com.blz.testjava.converter;

import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<ProductDTO, Product> {
    @Autowired private InventoryConverter inventoryConverter;

    @Override
    public Product perform(final ProductDTO input) {
        final Product result = new Product();
        result.setSku(input.getSku());
        result.setName(input.getName());
        result.setInventory(inventoryConverter.perform(input.getInventory()));

        return result;
    }
}
