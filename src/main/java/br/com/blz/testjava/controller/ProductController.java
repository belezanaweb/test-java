package br.com.blz.testjava.controller;

import br.com.blz.testjava.exception.ResourceAlreadyExistException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/api")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Add a new product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", ref = "error-404"),
        @ApiResponse(responseCode = "409", ref = "error-409"),
        @ApiResponse(responseCode = "500", ref = "error-500"),
    })
    @PostMapping(path = "add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> add(final @RequestBody Product product) {
        try {
            final Product savedProduct = productService.add(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (final ResourceAlreadyExistException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Operation(summary = "Get a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", ref = "error-404"),
        @ApiResponse(responseCode = "500", ref = "error-500"),
    })
    @GetMapping(path = "get/{sku}")
    public ResponseEntity<Product> get(@PathVariable long sku) {
        try {
            final Product savedProduct = productService.get(sku);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (final ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Operation(summary = "Update a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", ref = "error-404"),
        @ApiResponse(responseCode = "500", ref = "error-500"),
    })
    @PatchMapping(path = "update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> update(final @RequestBody Product product) {
        try {
            final Product savedProduct = productService.update(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (final ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", ref = "error-404"),
        @ApiResponse(responseCode = "500", ref = "error-500"),
    })
    @DeleteMapping(path = "delete/{sku}")
    public ResponseEntity<Product> delete(@PathVariable long sku) {
        try {
            productService.delete(sku);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (final ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
