package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.ProductModel;

public interface IProductRepository {
	
    void createProduct(final ProductModel productModel);

    void updateProduct(final ProductModel productModel);

    void deleteProduct(final Integer sku);

    ProductModel findProductBySku(final Integer sku);

}
