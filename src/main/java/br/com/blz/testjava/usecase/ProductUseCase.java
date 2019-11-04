package br.com.blz.testjava.usecase;

import br.com.blz.testjava.usecase.data.Product;

public interface ProductUseCase {

    Product getBySku(Long sku);
    Product create(Product product);
    Product update(Long sku, Product product);
    void delete(Long sku);

}
