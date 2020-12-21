package br.com.blz.testjava.ProductMother;

import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.controller.resources.ProductResponse;

import static br.com.blz.testjava.ProductMother.WarehouseResourcesMother.createWarehouse;
import static java.util.Collections.singleton;

public class ProductResourcesMother {

    private ProductResourcesMother() { }

    public static ProductResponse createProductResponse() {
        return new ProductResponse();
    }

    public static ProductRequest createProductRequest() {
        return new ProductRequest(123L, "Sample Product", singleton(createWarehouse()));
    }
}
