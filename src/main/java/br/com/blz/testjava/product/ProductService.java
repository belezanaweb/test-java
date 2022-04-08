package br.com.blz.testjava.product;

import br.com.blz.testjava.application.dto.product.ProductDto;
import br.com.blz.testjava.application.dto.product.ProductForm;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);
    public ProductDto findBySkuProduct(Long sku);
    public List<ProductDto> findAllProducts();
    public Long updateProduct(Long sku, ProductForm productForm) throws NoSuchFieldException;
    public void deleteProduct(Long sku);
}
