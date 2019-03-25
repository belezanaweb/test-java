package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;

/**
 * Defines an interface to product operations.
 */
public interface ProductService {

    /**
     * It saves a Product.
     * @param product - {@link Product}
     */
    void save(Product product);

    /**
     * Tries to update a Product by sku.
     * @param sku - Product identity.
     * @param product - Product to be updated.
     * @return Product - {@link Product}  updated.
     */
    Product update(Integer sku, Product product);

    /**
     * Tries to delete a Product by sku.
     * @param sku - Product identity.
     */
    void delete(Integer sku);

    /**
     * Tries to find a Product by sku.
     * @param sku - Product identity.
     * @return - {@link Product}.
     */
    Product find(Integer sku);
}
