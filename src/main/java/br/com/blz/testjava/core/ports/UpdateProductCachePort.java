package br.com.blz.testjava.core.ports;

import br.com.blz.testjava.core.domain.Product;

public interface UpdateProductCachePort {

    void updateProduct(Product product, String sku);

}
