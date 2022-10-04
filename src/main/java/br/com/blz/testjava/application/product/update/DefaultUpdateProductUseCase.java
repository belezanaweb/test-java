package br.com.blz.testjava.application.product.update;

import br.com.blz.testjava.application.product.retrieve.ProductOutput;
import br.com.blz.testjava.domain.exceptions.NotFoundException;
import br.com.blz.testjava.domain.product.Product;
import br.com.blz.testjava.domain.product.ProductGateway;
import br.com.blz.testjava.domain.product.Sku;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateProductUseCase extends UpdateProductUseCase {

    private final ProductGateway productGateway;

    public DefaultUpdateProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public ProductOutput execute(final UpdateProductCommand command) {
        final var sku = command.sku();
        final var name = command.name();
        final var inventory = command.inventory();
        final var newProduct = Product.newProduct(sku, name, inventory.warehouses());
        this.productGateway.findBySku(Sku.from(sku)).map(ProductOutput::from).orElseThrow(notFound(Sku.from(sku)));
        return ProductOutput.from(this.productGateway.update(newProduct));
    }

    private Supplier<NotFoundException> notFound(final Sku sku) {
        return () -> NotFoundException.with(Product.class, sku, "sku");
    }

}
