package br.com.blz.testjava.service

import br.com.blz.testjava.model.Product

interface ProductService {

    Product save(Product product);
    Product update(Product product);
    Product findById(Long sku);
    void deleteById(Long sku);


}
