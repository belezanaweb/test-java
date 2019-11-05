package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.data.ProductAttributes;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.ProductExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductSkuImmutableException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.bo.ProductBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductBySku(long sku){
        Product product = productRepository.findProductBySku(sku);

        if(product == null) throw new ProductNotFoundException();

        product.getInventory().setQuantity(0);

        product.getInventory().getWarehouses().stream().forEach(
            f-> product.getInventory().setQuantity(
                product.getInventory().getQuantity() + f.getQuantity()
            )
        );

        product.setIsMarketable(product.getInventory().getQuantity() > 0);

        return product;
    }

    public void deleteProduct(Long sku) {

        Product product = productRepository.findProductBySku(sku);

        if(product == null) throw new ProductNotFoundException();

        productRepository.deleteProduct(sku);
    }

    public void createProduct(ProductAttributes productAttributes) {
        Product product = ProductBO.parsePojoToEntity(productAttributes);

        if(productRepository.findProductBySku(product.getSku()) != null)
            throw new ProductExistsException();

        productRepository.addProduct(product);
    }

    public void update(Long sku, ProductAttributes productAttributes) {
        Product product = ProductBO.parsePojoToEntity(productAttributes);

        if(productRepository.findProductBySku(sku) == null)
            throw new ProductNotFoundException();

        if(sku.compareTo(product.getSku()) != 0)
            throw new ProductSkuImmutableException();

        productRepository.updateProduct(sku, product);
    }
}
