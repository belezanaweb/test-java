package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DuplicationException;
import br.com.blz.testjava.exception.NotFoundException;

public interface ProductService {

    Product create(Product product) throws DuplicationException;

    Product update(Product product, Long sku) throws NotFoundException;

    Product get(Long sku) throws NotFoundException;

    void delete(Long sku);
}