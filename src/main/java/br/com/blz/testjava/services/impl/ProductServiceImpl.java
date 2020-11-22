package br.com.blz.testjava.services.impl;

import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.exceptions.ProductUnavaliableException;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
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

        countProductsQuantity(product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductBySku(Long sku) {
        return this.productRepository.findBySku(sku).map(product -> {
            if (product.getInventory().getQuantity() == 0)
                throw new ProductUnavaliableException("The consulted product is sold out");

            int productToBeRemoved = 1;
            for (Warehouse warehouse : product.getInventory().getWarehouses()) {
                warehouse.setQuantity(warehouse.getQuantity() - productToBeRemoved);
                break;
            }

            product.getInventory()
                .setQuantity(product.getInventory().getQuantity() - productToBeRemoved);
            update(product);
            return this.productRepository.findBySku(sku).get();
        });
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
        if (product == null || product.getSku() == null) {
            throw new IllegalArgumentException("Product SKU can't be null");
        }
        countProductsQuantity(product);
        return productRepository.save(product);
    }

    private void countProductsQuantity(Product product) {
        int productsQuantity = product.getInventory().getWarehouses()
            .stream().mapToInt(Warehouse::getQuantity).sum();

        if (productsQuantity > 0) {
            product.getInventory().setQuantity(productsQuantity);
            product.setIsMarketable(Boolean.TRUE);
        }
    }

}
