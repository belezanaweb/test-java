package br.com.blz.testjava.services.impl;

import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.repository.ProductRepository;
import br.com.blz.testjava.services.ProductService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        if (productRepository.existsBySku(product.getSku()))
            throw new BusinessException("SKU already used by other product.");

        product = productRepository.save(product);

        Product finalProduct = product;
        product.getInventory().getWarehouses().forEach(w ->
            w.setInventory(finalProduct.getInventory()));

        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getBySku(Long sku) {
        return this.productRepository.findBySku(sku);
    }

    @Override
    public void delete(Product product) {
        if (product == null || product.getSku() == null) {
            throw new IllegalArgumentException("Product SKU can't be null");
        }
        this.productRepository.delete(product);
    }

    @Override
    public Product update(Product product) {
        return null;
    }
}
