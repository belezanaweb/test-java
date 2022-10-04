package br.com.blz.testjava.application.product.delete;

import br.com.blz.testjava.domain.product.ProductGateway;
import br.com.blz.testjava.domain.product.Sku;

import java.util.Objects;

public class DefaultDeleteProductUseCase extends DeleteProductUseCase {

    private final ProductGateway productGateway;

    public DefaultDeleteProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public void execute(final Long sku) {
        this.productGateway.deleteBySku(Sku.from(sku));
    }

}
