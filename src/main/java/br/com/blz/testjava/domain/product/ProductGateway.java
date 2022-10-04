package br.com.blz.testjava.domain.product;

import java.util.Optional;

public interface ProductGateway {

    Product create(Product product);

    void deleteBySku(Sku sku);

    Optional<Product> findBySku(Sku sku);

    Product update(Product product);

}
