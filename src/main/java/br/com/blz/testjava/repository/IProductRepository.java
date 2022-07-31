package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;

public interface IProductRepository {

    void insert(final Product product);

    void update(final Product product);

    void delete(final Integer sku);

    Product findBySku(final Integer sku);

}
