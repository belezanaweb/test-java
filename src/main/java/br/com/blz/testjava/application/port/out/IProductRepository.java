package br.com.blz.testjava.application.port.out;

import br.com.blz.testjava.application.domain.Product;

public interface IProductRepository {

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    Product findProduct(Long sku);

    void deleteProduct(Long sku);
}
