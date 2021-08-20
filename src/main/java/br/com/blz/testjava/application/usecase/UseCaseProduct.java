package br.com.blz.testjava.application.usecase;

import br.com.blz.testjava.application.domain.Product;
import br.com.blz.testjava.application.port.in.IUseCaseProduct;
import br.com.blz.testjava.application.port.out.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class UseCaseProduct implements IUseCaseProduct {

    private final IProductRepository productRepository;

    public UseCaseProduct(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public Product findProduct(Long sku) {
        return productRepository.findProduct(sku);
    }

    @Override
    public void deleteProduct(Long sku) {
        productRepository.deleteProduct(sku);
    }
}
