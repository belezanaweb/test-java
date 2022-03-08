package br.com.blz.testjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    public void create(ProductDTOIn productDTOIn){
        Product product =  new Product(productDTOIn);

    }


}
