package br.com.blz.testjava.services;

import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.entities.Warehouse;
import br.com.blz.testjava.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private transient ProductRepository productRepository;

    public Product insert(final Product product) {
        Optional<Product> productOp = this.findBySku(product.getSku().longValue());
        productOp.ifPresent(p -> {
            if(p.getSku().equals(product.getSku()))
                throw new IllegalArgumentException("Dois produtos são considerados iguais se os seus skus forem iguais");
        });
        return this.productRepository.save(product);
    }

    @CachePut(value = "products", key = "#product.sku")
    public Product update(final Product product) {
        Optional<Product> productOp = this.findBySku(product.getSku().longValue());
        if(!productOp.isPresent())
            throw new IllegalArgumentException("Produto inválido!");
        product.setId(productOp.get().getId());
        return this.productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#sku")
    @Transactional(readOnly = true)
    public Optional<Product> findBySku(final Long sku) {
        Optional<Product> product = this.productRepository.findBySku(sku);
        this.loadWarehouses(product);
        return product;
    }

    private void loadWarehouses(final Optional<Product> product) {
        product.ifPresent(p -> {
            if(p.getInventory() != null) {
                List<Warehouse> warehouseList = new ArrayList<>(p.getInventory().getWarehouses().size());
                warehouseList.addAll(p.getInventory().getWarehouses());
                product.get().getInventory().setWarehouses(warehouseList);
            }
        });
    }

    @CacheEvict(value = "products", key = "#sku", allEntries = true)
    public void delete(final Long sku) {
        Optional<Product> product = this.findBySku(sku);
        if(!product.isPresent())
            throw new IllegalArgumentException("Sku inválido!");
        this.productRepository.delete(product.get().getId());
    }

}
