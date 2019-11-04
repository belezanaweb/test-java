package br.com.blz.testjava.usecase.impl;

import br.com.blz.testjava.base.exceptions.DocumentNotExistsException;
import br.com.blz.testjava.gateway.documents.ProductDocument;
import br.com.blz.testjava.gateway.repository.ProductRepository;
import br.com.blz.testjava.usecase.ProductUseCase;
import br.com.blz.testjava.usecase.converter.ProductUseCaseConverter;
import br.com.blz.testjava.usecase.data.Inventory;
import br.com.blz.testjava.usecase.data.Product;
import br.com.blz.testjava.usecase.data.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validation;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;
import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

    private final static String INVENTORY_IS_REQUIRED = "Inventory is Required";
    private final static String WAREHOUSE_IS_REQUIRED = "Warehouse is Required";
    private final ProductUseCaseConverter converter;
    private final ProductRepository repository;

    @Override
    public Product getBySku(Long sku) {
        ProductDocument document = repository.getById(sku);
        Product product = converter.toResponse(document);
        handlerInventoryQuantityAndMarketable(product);

        return product;
    }

    @Override
    public Product create(@Validated Product product) {
        ProductDocument documentToCreate = converter.toRequest(product);
        ProductDocument createdDocument = repository.create(documentToCreate);
        Product createdProduct = converter.toResponse(createdDocument);
        handlerInventoryQuantityAndMarketable(createdProduct);
        return createdProduct;
    }

    @Override
    public Product update(Long sku,@Validated Product product) {
        product.setSku(sku);


        ProductDocument documentToUpdate = converter.toRequest(product);
        ProductDocument createdDocument = repository.update(documentToUpdate);
        Product updatedProduct = converter.toResponse(createdDocument);
        handlerInventoryQuantityAndMarketable(updatedProduct);
        return updatedProduct;
    }

    @Override
    public void delete(Long sku) {
        repository.delete(sku);
    }

    private void handlerInventoryQuantityAndMarketable(Product product){
        if(nonNull(product)){
            calculeInventoryQuantity(product);
            defineFlagMarketable(product);
        }
    }

    private void calculeInventoryQuantity(Product product) {
        Inventory inventory = product.getInventory();
        List<Warehouse> warehouses = inventory.getWarehouses();
        if(!warehouses.isEmpty()){
            Integer totalAmount = warehouses.stream().map(Warehouse::getQuantity)
                .reduce(0, Integer::sum);
           inventory.setQuantity(totalAmount);
        }
    }

    private void defineFlagMarketable(Product product) {

        Inventory inventory = product.getInventory();
        Integer inventoryQuantity = inventory.getQuantity();
        product.setMarketable(nonNull(inventoryQuantity) && inventoryQuantity > 0 );

    }


}
