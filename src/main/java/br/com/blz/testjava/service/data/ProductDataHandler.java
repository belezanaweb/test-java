package br.com.blz.testjava.service.data;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.data.exception.ProductSkuAlreadyExistsException;
import br.com.blz.testjava.service.data.exception.SkuNotProvidedException;

public interface ProductDataHandler {

    Product addProduct(Product product) throws SkuNotProvidedException, ProductSkuAlreadyExistsException;

    Product updateProduct(Product newProduct);

    Product getProduct(Long sku);

    boolean removeProduct(Long sku);
}
