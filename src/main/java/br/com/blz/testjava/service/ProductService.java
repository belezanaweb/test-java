package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    public Product createProduct(ProductDTO product);
    public Product findProductBySKU(Integer sku);
    public Product editProduct(Integer sku, ProductDTO product);
    public void deleteProductBySku(Integer sku);
}
