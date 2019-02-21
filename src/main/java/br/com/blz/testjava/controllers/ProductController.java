package br.com.blz.testjava.controllers;


import br.com.blz.testjava.controllers.dto.request.ProductRequest;
import br.com.blz.testjava.controllers.dto.response.ProductResponse;
import br.com.blz.testjava.controllers.dto.response.ResponseDto;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.services.ProductService;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/products", produces = { "application/vnd.testjava.api.v1+json" })
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private transient ProductService productService;

    @Autowired
    private transient ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto> insert(@RequestBody @Valid ProductRequest body) {
        Product product = this.productService.insert(this.mapper.map(body, Product.class));
        ProductResponse response = this.mapper.map(product, ProductResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(response.getSku()));
    }

    @GetMapping(path = "/{sku}")
    public ResponseEntity<ProductResponse> getBySku( @PathVariable Long sku) {
        Product product = this.productService.findBySku(sku)
            .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado nenhum produto por este SKU"));
        ProductResponse response = this.mapper.map(product, ProductResponse.class);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{sku}")
    public ResponseEntity<Long> update(
        @PathVariable Long sku,
        @RequestBody @Valid ProductRequest body) {
        LOGGER.info("UPDATE PARAMTER - REQUEST: {}", body);
        body.setSku(sku);
        Product product = this.mapper.map(body, Product.class);
        val response = this.productService.update(product);
        if(response != null)
            return ResponseEntity.status(HttpStatus.OK).body(this.mapper.map(response, ProductResponse.class).getSku());
        else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/{sku}")
    public void delete(@PathVariable Long sku){
        LOGGER.info("DELETE SKU Number {}", sku);
        this.productService.delete(sku);
    }

}
