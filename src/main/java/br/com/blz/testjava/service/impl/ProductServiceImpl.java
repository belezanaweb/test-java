package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BlzException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.blz.testjava.enums.MessagesEnum.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Product product) {

        try {

            if(this.productRepository.find(product.getSku()) != null) {
                throw new BlzException(DUPLICATED_PRODUCT_ERROR.getMessage());
            }

            this.productRepository.save(product);

        } catch (BlzException e) {
            throw e;

        } catch (Exception e) {
            throw new BlzException(OPERATION_ERROR.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product update(Integer sku, Product product) {

        try {

            if(this.find(sku) != null) {
                product.setSku(sku);
                this.productRepository.save(product);
            }

        } catch (BlzException e) {
            throw e;

        } catch (Exception e) {
            throw new BlzException(OPERATION_ERROR.getMessage());
        }

        return product;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer sku) {

        try {
            this.productRepository.delete(sku);

        } catch (BlzException e) {
            throw e;

        } catch (Exception e) {
            throw new BlzException(OPERATION_ERROR.getMessage());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product find(Integer sku) {

        Product product;

        try {
            product = this.productRepository.find(sku);

            if (product == null) {
                throw new BlzException(String.format(NO_PRODUCT_ERROR.getMessage(), sku));
            }

        } catch (BlzException e) {
            throw e;

        } catch (Exception e) {
            throw new BlzException(OPERATION_ERROR.getMessage());
        }

        return product;
    }
}
