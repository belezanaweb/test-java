package br.com.blz.testjava.application.product.create;

import br.com.blz.testjava.domain.product.Product;
import br.com.blz.testjava.domain.product.ProductGateway;

import java.util.Objects;

public class DefaultCreateProductUseCase extends CreateProductUseCase {

    private final ProductGateway productGateway;

    public DefaultCreateProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public CreateProductOutput execute(final CreateProductCommand command) {
        final var sku = command.sku();
        final var name = command.name();
        final var inventory = command.inventory();
        final var newProduct = Product.newProduct(sku, name, inventory.warehouses());
        return CreateProductOutput.from(this.productGateway.create(newProduct));
    }

}
