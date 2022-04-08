package br.com.blz.testjava.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public Product saveProduct(Product product);
    public Optional<Product> findBySkuProduct(Long sku);
    public List<Product> findAllProducts();
    public void updateProduct(Product product);
    public void deleteProduct(Long sku);
}
