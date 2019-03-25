package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.response.BlzResponse;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BlzException;
import br.com.blz.testjava.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.blz.testjava.enums.MessageEnum.SUCESS;
import static org.springframework.http.HttpStatus.*;

@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<BlzResponse> save(@RequestBody Product product) {

        BlzResponse<Product> blzResponse;

        try {
            this.productService.save(product);
            blzResponse = new BlzResponse<>(OK.value(), SUCESS.getMessage(), product);

        } catch (BlzException e) {
            LOGGER.error(e.getMessage(), e);
            blzResponse = new BlzResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage(), product);
        }

        return new ResponseEntity<>(blzResponse, valueOf(blzResponse.getHttpStatus()));
    }

    @GetMapping("/product/{sku}")
    public ResponseEntity<BlzResponse> find(@PathVariable Integer sku) {

        BlzResponse<Product> blzResponse;

        try {
            Product product = this.productService.find(sku);
            blzResponse = new BlzResponse<>(OK.value(), product);

        } catch (BlzException e) {
            LOGGER.error(e.getMessage(), e);
            blzResponse = new BlzResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

        return new ResponseEntity<>(blzResponse, valueOf(blzResponse.getHttpStatus()));
    }

    @PutMapping("/product/{sku}")
    public ResponseEntity<BlzResponse> update(@PathVariable Integer sku, @RequestBody Product product) {

        BlzResponse<Product> blzResponse;

        try {
            product = this.productService.update(sku, product);
            blzResponse = new BlzResponse<>(OK.value(), SUCESS.getMessage(), product);

        } catch (BlzException e) {
            LOGGER.error(e.getMessage(), e);
            blzResponse = new BlzResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

        return new ResponseEntity<>(blzResponse, valueOf(blzResponse.getHttpStatus()));
    }

    @DeleteMapping("/product/{sku}")
    public ResponseEntity<BlzResponse> delete(@PathVariable Integer sku) {

        BlzResponse<Product> blzResponse;

        try {
            this.productService.delete(sku);
            blzResponse = new BlzResponse<>(OK.value(), SUCESS.getMessage());

        } catch (BlzException e) {
            LOGGER.error(e.getMessage(), e);
            blzResponse = new BlzResponse<>(INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

        return new ResponseEntity<>(blzResponse, valueOf(blzResponse.getHttpStatus()));
    }
}
