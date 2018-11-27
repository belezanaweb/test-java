package br.com.blz.testjava.gateways;

import br.com.blz.testjava.domains.Product;
import java.util.List;

public interface ProductGateway {

    Product findProductBySku(Integer sku);

    List<Product> findAllProducts();

    void createProduct(Product product);

    void deleteProductBySku(Integer sku);
}
