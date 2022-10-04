package br.com.blz.testjava.application.product.retrieve;

import br.com.blz.testjava.domain.exceptions.NotFoundException;
import br.com.blz.testjava.domain.product.Product;
import br.com.blz.testjava.domain.product.ProductGateway;
import br.com.blz.testjava.domain.product.Sku;

import java.util.Objects;
import java.util.function.Supplier;

import static br.com.blz.testjava.domain.product.Sku.from;

public class DefaultGetProductBySkuUseCase extends GetProductBySkuUseCase {

    private final ProductGateway productGateway;

    public DefaultGetProductBySkuUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public ProductOutput execute(final Long in) {
        final var sku = from(in);
        return this.productGateway.findBySku(sku).map(ProductOutput::from).orElseThrow(notFound(sku));
    }

    private Supplier<NotFoundException> notFound(final Sku sku) {
        return () -> NotFoundException.with(Product.class, sku, "sku");
    }
}
