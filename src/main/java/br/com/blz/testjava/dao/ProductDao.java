package br.com.blz.testjava.dao;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DuplicationException;
import br.com.blz.testjava.exception.NotFoundException;

public interface ProductDao {
 
    void save(Product product) throws DuplicationException;

    void update(Product product, Long sku) throws NotFoundException;

    Product findBySku(Long sku) throws NotFoundException;

    void delete(Long sku);
}
