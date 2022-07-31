package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;

public interface IProductRepository {

    void save(final Product product);

    void delete(final Integer sku);

    Product findBySku(final Integer sku);

}
