package br.com.blz.testjava.application.product.create;

import br.com.blz.testjava.domain.product.Product;

public record CreateProductOutput(Long sku) {

    public static CreateProductOutput from(final Long sku) {
        return new CreateProductOutput(sku);
    }

    public static CreateProductOutput from(final Product product) {
        return new CreateProductOutput(product.getId().getValue());
    }
}
