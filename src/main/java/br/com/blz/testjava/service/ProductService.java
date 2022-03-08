package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.in.ProductDTOIn;
import br.com.blz.testjava.dto.out.ProductDTOOut;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exceptions.ProductFoundException;
import br.com.blz.testjava.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    public void create(ProductDTOIn productDTOIn) throws ProductFoundException {
        Product exists = this.productRepository.get(productDTOIn.getSku());
        if (exists != null && exists.getSku() == productDTOIn.getSku())
            throw new ProductFoundException(exists.getSku());

        Product product = new Product(productDTOIn);
        this.productRepository.save(product);
    }

    public ProductDTOOut get(int sku) {
        Product product = this.productRepository.get(sku);
        return new ProductDTOOut(product);

    }

    public void remove(int sku) {
        this.productRepository.delete(sku);
    }

    public ProductDTOOut update(int sku, ProductDTOIn productDTOIn) {
        productDTOIn.setSku(sku);
        Product edited = this.productRepository.edit(new Product(productDTOIn));
        return new ProductDTOOut(edited);
    }


}
