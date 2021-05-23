package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.ResourceAlreadyExistException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product add(final Product product) {
        if (productRepository.existsById(product.getSku())) {
            throw new ResourceAlreadyExistException(String.format("Product with id %s already exists.", product.getSku()));
        }

        return productRepository.save(product);
    }

    public Product get(final long sku) {
        final Optional<Product> optionalSavedProduct = productRepository.findById(sku);
        final Product product = optionalSavedProduct
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s does not exists.", sku)));
        final long qtdWarehouse = product.getInventory().getWarehouses().stream()
            .filter(warehouse -> warehouse.getQuantity() >= 0)
            .mapToLong(Warehouse::getQuantity)
            .sum();

        product.setMarketable(qtdWarehouse > 0);
        product.getInventory().setQuantity(qtdWarehouse);

        return product;
    }

    public Product update(final Product product) {
        final Optional<Product> optionalOldProduct = productRepository.findById(product.getSku());
        optionalOldProduct.orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s does not exists.", product.getSku())));

        product.getInventory().setQuantity(0L);
        product.setMarketable(false);

        return productRepository.save(product);
    }

    public void delete(final long sku) {
        if (!productRepository.existsById(sku)) {
            throw new ResourceNotFoundException(String.format("Product with id %s already exists.", sku));
        }
        productRepository.deleteById(sku);
    }
}
