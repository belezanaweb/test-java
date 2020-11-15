package br.com.blz.testjava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.blz.testjava.converters.GenericConverter;
import br.com.blz.testjava.dtos.CreateProductRequest;
import br.com.blz.testjava.dtos.EditProductRequest;
import br.com.blz.testjava.dtos.ProductResponse;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;
    GenericConverter converter; 

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        converter = new GenericConverter();
    }

    public ProductResponse getProduct(Long sku) {
        var product = getProductBySku(sku);
        var response = (ProductResponse) converter.merge(product,new ProductResponse());
        return response;
    }

    public void deleteProduct(Long sku) {
        repository.deleteBySku(sku);
    }

    public ProductResponse createProduct(CreateProductRequest productRequest) {
        if(productAlreadyExists(productRequest.getSku()))
            throw new ProductAlreadyExistsException();
        var product = (Product) converter.merge(productRequest,new Product());
        return saveProduct(product);
    }

    public ProductResponse editProduct(Long sku, EditProductRequest productRequest) {
        var product = getProductBySku(sku);
        product = (Product) converter.merge(productRequest, product);
        return saveProduct(product);
    }


    private Product getProductBySku(Long sku) {
        var product = repository.findBySku(sku);
        if(product == null)
            throw new ProductNotFoundException();

        return product;
    }

    private ProductResponse saveProduct(Product product) {
        var savedEntity = repository.save(product);
        return (ProductResponse) converter.merge(savedEntity, new ProductResponse());
    }

    private boolean productAlreadyExists(Long sku) {
        var product = repository.findBySku(sku);

        return product != null;
    }
}
