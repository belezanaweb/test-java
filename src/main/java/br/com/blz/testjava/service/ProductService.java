package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.controller.response.ProductResponse;

public interface ProductService {
    
    ProductResponse findBySku(long sku);
    
    ProductResponse save(ProductRequest productRequest);
    
    ProductResponse update(ProductRequest productRequest);
    
    void remove(long sku);
    
    void clean();
}
