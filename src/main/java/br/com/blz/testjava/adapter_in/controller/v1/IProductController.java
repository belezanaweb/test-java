package br.com.blz.testjava.adapter_in.controller.v1;

import br.com.blz.testjava.adapter_in.request.ProductRequest;
import br.com.blz.testjava.adapter_in.response.ProductResponse;
import br.com.blz.testjava.adapter_in.util.ConstantsNames;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(ConstantsNames.URI_VERSION_NAME)
public interface IProductController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    ProductResponse updateProduct(@Valid @RequestBody ProductRequest productRequest);

    @GetMapping(ConstantsNames.URI_SKU)
    @ResponseStatus(HttpStatus.OK)
    ProductResponse findProduct(@PathVariable Long sku);

    @DeleteMapping(ConstantsNames.URI_SKU)
    @ResponseStatus(HttpStatus.OK)
    void deleteProduct(@PathVariable Long sku);
}
