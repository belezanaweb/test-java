package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.request.ProductRequestDTO;
import br.com.blz.testjava.dto.response.ProductResponseDTO;
import br.com.blz.testjava.dto.response.Response;
import br.com.blz.testjava.exception.GenericErrorException;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "produtos", description = "Product API")
public interface ProductApi {

    @ApiOperation(value = "Create product by sku")
    @ApiResponses(
        value = {
            @ApiResponse(code = 201, message = "Create product"),
            @ApiResponse(code = 500, message = "Internal server error",
                response = GenericErrorException.class)
        })
    @PostMapping
    ResponseEntity<Response> create(@RequestBody ProductRequestDTO body);

    @ApiOperation(value = "Update a Product")
    @ApiResponses(
        value = {
            @ApiResponse(code = 204, message = "Update"),
            @ApiResponse(code = 500, message = "Internal server error",
                response = GenericErrorException.class)
        })
    @PutMapping(value = "/{sku}")
    ResponseEntity<Response> update(
        @PathVariable Integer sku, @RequestBody ProductRequestDTO body);

    @ApiOperation(value = "Select Product by Sky",
        response = ProductResponseDTO.class)
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Success",
                response = ProductResponseDTO.class),
            @ApiResponse(code = 500, message = "Internal server error",
                response = GenericErrorException.class)
        })
    @GetMapping(value = "/{sku}")
    ResponseEntity<ProductResponseDTO> findBySku(@PathVariable Integer sku);

    @ApiOperation(value = "Delete product by sku")
    @ApiResponses(
        value = {
            @ApiResponse(code = 204, message = "Delete"),
            @ApiResponse(code = 500, message = "Internal server error",
                response = GenericErrorException.class)})
    @DeleteMapping(value = "/{sku}")
    ResponseEntity<Void> delete(@PathVariable Integer sku);

}
