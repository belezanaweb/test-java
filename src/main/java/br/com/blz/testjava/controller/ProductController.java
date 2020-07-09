package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(ProductController.EXTRA_FIELD_PATH)
public class ProductController {

    static final String EXTRA_FIELD_PATH = "/v1/product";

    @Autowired
    private ProductService productService;

    @RequestMapping(
        value = "/{sku}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    public Product listProduct(@PathVariable("sku") @NotNull String sku) {
        return productService.listProduct(sku);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.verifyAndCreateProduct(product);
    }

    @RequestMapping(
        value = "/{sku}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    public Product updateProduct(@PathVariable("sku") @NotNull String sku, @RequestBody Product product) {
        return productService.updateProduct(sku, product);
    }

    @RequestMapping(
        value = "/{sku}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("sku") @NotNull String sku) {
        productService.deleteProduct(sku);
    }
}
