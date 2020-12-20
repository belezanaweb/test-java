package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.model.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    public static final String PRODUCT_NOT_CREATED_SKU_ALREADY_EXISTS = "Product not created. Product (Sku: %d) already exists";
    public static final String PRODUCT_NOT_UPDATED_SKU_NOT_FOUND = "Product not updated. Product (Sku: %d) not found)";
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(
        ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        log.debug("Request to get all Products");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> find(Long id) {
        log.debug("Request to get Product : {}", id);
        return Optional.ofNullable(productRepository.find(id));
    }

    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        Product saved = productRepository.save(product);
        if (saved == null) {
            throw new ValidationException(
                String.format(PRODUCT_NOT_CREATED_SKU_ALREADY_EXISTS,
                    product.getSku()));
        }
        return saved;
    }

    @Override
    public Product update(Product product) throws ValidationException {
        log.debug("Request to edit Product : {}", product);
        Product updated = productRepository.update(product);
        if (updated == null) {
            throw new ValidationException(
                String.format(PRODUCT_NOT_UPDATED_SKU_NOT_FOUND,
                    product.getSku()));
        }
        return updated;
    }

    @Override
    public Boolean delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        return productRepository.delete(id) != null;
    }
}
