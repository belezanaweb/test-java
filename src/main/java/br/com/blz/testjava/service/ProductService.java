package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import br.com.blz.testjava.domain.api.request.ReplaceProductRequest;
import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.FindProductResponse;
import br.com.blz.testjava.domain.exception.ConflictException;
import br.com.blz.testjava.domain.exception.NotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public CreateProductResponse createProduct(CreateProductRequest request) {
        if (repository.findBySku(request.getSku()).isPresent()) {
            throw new ConflictException();
        }

        return repository.insert(request).toCreateResponse();
    }

    public FindProductResponse findProduct(String sku) {
        return repository.findBySku(sanitizeSku(sku))
            .map(CreateProductRequest::toFindResponse)
            .orElseThrow(NotFoundException::new);
    }

    public CreateProductResponse replaceProduct(String sku, ReplaceProductRequest request) {
        Long productKey = sanitizeSku(sku);

        CreateProductRequest product = new CreateProductRequest();
        product.setSku(productKey);
        product.setName(request.getName());
        product.setInventory(request.getInventory());

        boolean productUpdated = repository.findBySku(productKey).isPresent();

        return repository.insert(product).toCreateResponse(productUpdated);
    }

    public void deleteProduct(String sku) {
        repository.delete(sanitizeSku(sku))
            .orElseThrow(NotFoundException::new);
    }

    private Long sanitizeSku(String sku) {
        try {
            return Long.valueOf(sku);
        } catch (NumberFormatException e) {
            throw new NotFoundException();
        }
    }
}
