package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.exception.ProductDuplicateException;
import br.com.blz.testjava.exception.ProductNotFoundException;

import java.util.List;

public class ProductService {

    ProductRepository productRepository = new ProductRepository();

    public Product insert(Product product) {
        if(productRepository.getBySku(product.getSku()) == null){
            return productRepository.insert(product);
        }else{
            throw new ProductDuplicateException();
        }

    }

    public Product update(Product product, long sku){

        if(productRepository.getBySku(sku) == null){
            throw new ProductNotFoundException();
        }

        if(product.getSku() == sku){
            return productRepository.update(product, sku);
        }

        if(productRepository.getBySku(product.getSku()) == null){
            return productRepository.update(product, sku);
        }else{
            throw new ProductDuplicateException();
        }

    }

    public Product getBySku(long sku){

        Product product = productRepository.getBySku(sku);

        if(product == null){
            throw new ProductNotFoundException();
        }

        return product;
    }

    public List<Product> delete(long sku){

        if(productRepository.getBySku(sku) == null){
            throw new ProductNotFoundException();
        }

        return productRepository.delete(sku);
    }

    public List<Product> getAll(){
        return productRepository.getAll();
    }
}
