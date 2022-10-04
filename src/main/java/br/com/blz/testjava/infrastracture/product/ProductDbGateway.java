package br.com.blz.testjava.infrastracture.product;

import br.com.blz.testjava.domain.product.Product;
import br.com.blz.testjava.domain.product.ProductGateway;
import br.com.blz.testjava.domain.product.Sku;
import br.com.blz.testjava.infrastracture.product.persistence.ProductEntity;
import br.com.blz.testjava.infrastracture.product.persistence.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDbGateway implements ProductGateway {

    private final ProductRepository repository;

    public ProductDbGateway(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(final Product product) {
        return this.repository.save(ProductEntity.from(product)).toAggregate();
    }

    @Override
    public void deleteBySku(final Sku sku) {
        this.repository.delete(sku.getValue());
    }

    @Override
    public Optional<Product> findBySku(final Sku sku) {
        return this.repository.findBySku(sku.getValue()).map(ProductEntity::toAggregate);
    }

    @Override
    public Product update(final Product product) {
        return this.repository.update(ProductEntity.from(product)).toAggregate();
    }

}
