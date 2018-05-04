package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BadRequestException;
import br.com.blz.testjava.exception.ConflictException;
import br.com.blz.testjava.exception.DataIntegrityViolationException;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void create(Product product) {
        try {
            repository.save(product);
        } catch (DataIntegrityViolationException dive) {
            throw new ConflictException("Product with sku=" + product.getSku() + " already exists");
        }
    }

    public Product findBySku(Integer sku) {
        return repository.findBySku(sku).orElseThrow(() -> new NotFoundException("Product with sku=" + sku + " not found"));
    }

    public void remove(Integer sku) {

        boolean removed = repository.remove(sku);

        if (!removed) {
            throw new NotFoundException("Product with sku=" + sku + " not found");
        }

    }

    public void updateProduct(Product product) {
        try {
            repository.update(product);
        } catch (DataIntegrityViolationException dive) {
            throw new BadRequestException("Product with sku=" + product.getSku() + " doesn't exists");
        }
    }

}
