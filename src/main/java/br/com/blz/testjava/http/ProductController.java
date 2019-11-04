package br.com.blz.testjava.http;

import br.com.blz.testjava.http.converter.ProductHttpConverter;
import br.com.blz.testjava.http.data.request.ProductRequest;
import br.com.blz.testjava.http.data.response.ProductResponse;
import br.com.blz.testjava.usecase.ProductUseCase;
import br.com.blz.testjava.usecase.data.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/products" )
@RequiredArgsConstructor
public class ProductController {

    private final ProductHttpConverter converter;
    private final ProductUseCase useCase;

    @GetMapping(path = "/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getProductBySku(@PathVariable Long sku){
        Product product = useCase.getBySku(sku);
        return converter.toResponse(product);
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse createProduct(@Valid @Validated @RequestBody ProductRequest request){
        Product convertedProduct = converter.toRequest(request);
        Product createdProduct = useCase.create(convertedProduct);
        return converter.toResponse(createdProduct);
    }

    @PutMapping(path = "/{sku}",  produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateProduct(@PathVariable Long sku, @Validated @RequestBody ProductRequest request){
        Product convertedProduct = converter.toRequest(request);
        Product updatedProduct = useCase.update(sku, convertedProduct);
        return converter.toResponse(updatedProduct);
    }

    @DeleteMapping(path = "/{sku}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable Long sku){
        useCase.delete(sku);
    }




}
