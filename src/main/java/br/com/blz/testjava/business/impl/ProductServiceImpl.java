package br.com.blz.testjava.business.impl;

import br.com.blz.testjava.business.ProductService;
import br.com.blz.testjava.exception.ExistingProductException;
import br.com.blz.testjava.exception.InvalidProductSKUException;
import br.com.blz.testjava.exception.NonExistingProductException;
import br.com.blz.testjava.exception.NullProductException;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.validators.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(Validators.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private void validateExistingProduct(String sku) throws InvalidProductSKUException, ExistingProductException {

        Product product = findProduct(sku);

        if (nonNull(product) && nonNull(product.getSku())) {
            logger.error(String.format(ExistingProductException.MSG, sku));
            throw new ExistingProductException(sku);
        }
    }

    private void validateNonExistingProduct(String sku) throws InvalidProductSKUException, NonExistingProductException {

        Product product = findProduct(sku);

        if (isNull(product)) {
            logger.error(String.format(NonExistingProductException.MSG, sku));
            throw new NonExistingProductException(sku);
        }
    }

    @Override
    public Product findProduct(String sku) throws InvalidProductSKUException {

        Validators.isValidSKU(sku);

        return productRepository.findProduct(sku);
    }

    @Override
    public Product createProduct(Product product) throws ExistingProductException, InvalidProductSKUException, NullProductException {

        Validators.isValidProduct(product);

        validateExistingProduct(product.getSku());

        return productRepository.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product) throws InvalidProductSKUException, NonExistingProductException {

        validateNonExistingProduct(product.getSku());

        return productRepository.updateProduct(product);
    }

    @Override
    public void removeProduct(String sku) throws InvalidProductSKUException, NullProductException {

        Product product = findProduct(sku);

        Validators.isValidProduct(product);

        productRepository.deleteProduct(sku);
    }
}
