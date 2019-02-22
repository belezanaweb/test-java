package br.com.blz.testjava.services;

import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Product update(final Product product) {
        Optional<Product> productOp = this.findBySku(product.getSku().longValue());
        if(!productOp.isPresent())
            throw new IllegalArgumentException("Produto inválido!");
        product.setId(productOp.get().getId());
        return this.productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findBySku(final Long sku) {
        Optional<Product> product = this.productRepository.findBySku(sku);
        if(product.isPresent()) {
            Product p = product.get();
            if(p.getInventory() != null) {
                Number quantity = p.getInventory().getWarehouses().stream().mapToInt(q -> q.getQuantity().intValue()).sum();
                p.getInventory().setQuantity(quantity);

                boolean isMarketable = (quantity.intValue() > 0);
                p.setIsMarketable(isMarketable);
            }
        }
        return product;
    }

    public void delete(Long sku) {
        Optional<Product> product = this.findBySku(sku);
        if(!product.isPresent())
            throw new IllegalArgumentException("Sku inválido!");
        this.productRepository.delete(product.get().getId());
    }

}
