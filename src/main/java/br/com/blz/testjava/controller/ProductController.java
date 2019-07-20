package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import br.com.blz.testjava.domain.api.request.ReplaceProductRequest;
import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.ProductResponse;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public ResponseEntity<CreateProductResponse> create(@RequestBody @Valid CreateProductRequest request) {
        repository.insert(request);

        CreateProductResponse response = new CreateProductResponse();
        response.setSku(request.getSku());
        response.setInventoryQuantity(request.retrieveInventoryQuantity());
        response.setMarketable(response.getInventoryQuantity() > 0);

        return ResponseEntity.created(getLocation(request)).body(response);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponse> read(@PathVariable("sku") String sku) {
        return ResponseEntity.of(repository.find(Long.valueOf(sku)).map(CreateProductRequest::toResponse));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductResponse> replace(@PathVariable("sku") String sku,
                                                   @RequestBody @Valid ReplaceProductRequest request) {
        CreateProductRequest product = new CreateProductRequest();
        product.setSku(Long.valueOf(sku));
        product.setName(request.getName());
        product.setWarehouses(request.getWarehouses());

        return ResponseEntity.ok(repository.insert(product).toResponse());
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable("sku") String sku) {
        repository.delete(Long.valueOf(sku));
        return ResponseEntity.noContent().build();
    }

    private URI getLocation(@RequestBody @Valid CreateProductRequest request) {
        try {
            return new URI("/product/" + request.getSku());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
