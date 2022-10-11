package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.ProductEntity;

public interface ProductRepository {

    void createProduct(final ProductEntity productEntity);

    void updateProduct(final ProductEntity productModel);

    void deleteProduct(final Integer sku);

    ProductEntity findProductBySku(final Integer sku);
}
