package br.com.blz.testjava.mother;

import br.com.blz.testjava.controller.resources.InventoryRequest;
import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.controller.resources.ProductResponse;
import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.persistence.entity.Product;

import java.util.HashSet;

import static br.com.blz.testjava.mother.WarehouseResourcesMother.createWarehouse;
import static com.fasterxml.uuid.Generators.randomBasedGenerator;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;

public class ProductMother {

    private ProductMother() { }

    public static ProductResponse createProductResponse() {
        return new ProductResponse();
    }

    public static ProductRequest createProductRequest() {
        return new ProductRequest(123L, "Sample Product", new InventoryRequest(singleton(createWarehouse())));
    }

    public static ProductRequest createProductRequestWithTwoWarehouses() {
        return new ProductRequest(123L, "Sample Product",
            new InventoryRequest(new HashSet<>(asList(
                createWarehouse(WarehouseType.ECOMMERCE, "Winterfell", 10),
                createWarehouse(WarehouseType.PHYSICAL_STORE, "Buique", 10)))));
    }

    public static Product createProductEntity() {
        return new Product(randomBasedGenerator().generate(), createProductRequestWithTwoWarehouses());
    }

    public static Product createSimpleProductEntity() {
        return new Product(randomBasedGenerator().generate(), createProductRequest());
    }
}
