package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.ProductDao;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DuplicationException;
import br.com.blz.testjava.exception.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product create(Product product) throws DuplicationException {
        productDao.save(product);
        return product;
    }

    @Override
    public Product update(Product product, Long sku) throws NotFoundException {
        productDao.update(product, sku);
        return product;
    }

    @Override
    public Product get(Long sku) throws NotFoundException {
        return productDao.findBySku(sku);
    }

    @Override
    public void delete(Long sku) {
        productDao.delete(sku);
    }
}
