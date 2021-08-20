package br.com.blz.testjava.application.port.in;

import br.com.blz.testjava.application.domain.Product;

public interface IUseCaseProduct {

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product findProduct(Long sku);

    void deleteProduct(Long sku);
}
