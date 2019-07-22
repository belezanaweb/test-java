package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import br.com.blz.testjava.domain.api.request.ReplaceProductRequest;
import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.FindProductResponse;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.blz.testjava.controller.BaseController.PRODUCT_ROOT;

@RestController
@RequestMapping(PRODUCT_ROOT)
public class ProductController extends BaseController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<CreateProductResponse> create(@RequestBody @Valid CreateProductRequest request) {
        logRequest(request);

        CreateProductResponse product = service.createProduct(request);
        ResponseEntity<CreateProductResponse> response = ResponseEntity.created(getLocation(product)).body(product);

        logResponse(response);
        return response;
    }

    @GetMapping(SKU_PATH)
    public ResponseEntity<FindProductResponse> read(@PathVariable(SKU_PLACEHOLDER) String sku) {
        logRequest(sku, HttpMethod.GET);

        FindProductResponse product = service.findProduct(sku);
        ResponseEntity<FindProductResponse> response = ResponseEntity.ok(product);

        logResponse(getURI(sku), HttpMethod.GET, response);
        return response;
    }

    @PutMapping(SKU_PATH)
    public ResponseEntity<CreateProductResponse> replace(@PathVariable(SKU_PLACEHOLDER) String sku,
                                                   @RequestBody @Valid ReplaceProductRequest request) {
        logRequest(getURI(sku), HttpMethod.PUT, request);
        CreateProductResponse product = service.replaceProduct(sku, request);

        ResponseEntity<CreateProductResponse> response;

        if (product.getUpdated()) {
            response = ResponseEntity.ok(product);
        } else {
            response = ResponseEntity.created(getLocation(sku)).body(product);
        }

        logResponse(getURI(sku), HttpMethod.PUT, response);
        return response;
    }

    @DeleteMapping(SKU_PATH)
    public ResponseEntity<Void> delete(@PathVariable(SKU_PLACEHOLDER) String sku) {
        logRequest(sku, HttpMethod.DELETE);

        service.deleteProduct(sku);
        ResponseEntity<Void> response = ResponseEntity.noContent().build();

        logResponse(getURI(sku), HttpMethod.DELETE, response);
        return response;
    }
}
