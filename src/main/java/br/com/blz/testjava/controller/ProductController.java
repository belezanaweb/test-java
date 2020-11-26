package br.com.blz.testjava.controller;

import br.com.blz.testjava.business.ProductService;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.exception.ExistingProductException;
import br.com.blz.testjava.exception.InvalidProductSKUException;
import br.com.blz.testjava.exception.NonExistingProductException;
import br.com.blz.testjava.exception.NullProductException;
import br.com.blz.testjava.mappers.ProductMapper;
import br.com.blz.testjava.model.request.ProductRequest;
import br.com.blz.testjava.model.response.ProductResponse;
import io.swagger.annotations.ApiOperation;
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
import javax.validation.constraints.NotBlank;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/product-controller")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper mapper;

    public ProductController(ProductMapper mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    @GetMapping("/SKU/{sku}")
    @ApiOperation(value = "Retorna o Produto por inventório: Local/Quantidade/Tipo ")
    public ResponseEntity<ProductResponse> findProduct(
        @PathVariable("sku") @Valid @NotBlank String sku
    ) throws InvalidProductSKUException {
        return ok(mapper.mapProductToProductResponse(productService.findProduct(sku)));
    }

    @PostMapping("/")
    @ApiOperation(value = "Cria um produto")
    public ResponseEntity<ProductResponse> createProduct(
        @RequestBody @Valid ProductRequest productRequest
    ) throws ExistingProductException, InvalidProductSKUException, NullProductException {
        Product product = mapper.mapProductRequestToProduct(productRequest);
        return ok(mapper.mapProductToProductResponse(productService.createProduct(product)));
    }

    @PutMapping("/")
    @ApiOperation(value = "Atualiza um produto")
    public ResponseEntity<ProductResponse> updateProduct(
        @RequestBody @Valid ProductRequest productRequest
    ) throws InvalidProductSKUException, NonExistingProductException {
        Product product = mapper.mapProductRequestToProduct(productRequest);
        return ok(mapper.mapProductToProductResponse(productService.updateProduct(product)));
    }

    @DeleteMapping("/SKU/{sku}")
    @ApiOperation(value = "Remove o Produto por SKU ")
    public ResponseEntity<Void> removeProduct(
        @PathVariable("sku") @Valid @NotBlank String sku
    ) throws InvalidProductSKUException, NullProductException {
        productService.removeProduct(sku);
        return ok().build();
    }

}
