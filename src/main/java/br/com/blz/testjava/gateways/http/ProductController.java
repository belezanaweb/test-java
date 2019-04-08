package br.com.blz.testjava.gateways.http;

import br.com.blz.testjava.exceptions.ConflictException;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.gateways.http.converter.ProductConverter;
import br.com.blz.testjava.gateways.http.converter.ProductRequestJSON;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;
import br.com.blz.testjava.usecases.ProductCreate;
import br.com.blz.testjava.usecases.ProductDelete;
import br.com.blz.testjava.usecases.ProductGet;
import br.com.blz.testjava.usecases.ProductUpdate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
    value = "/api/product",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductCreate create;
    private final ProductUpdate update;
    private final ProductGet get;
    private final ProductDelete delete;
    private final ProductConverter converter;

    @ApiOperation(value = "Create a new Product", response = ProductResponseJSON.class)
    @ApiResponses(
        value = {
            @ApiResponse(code = 201, message = "Created",
                response = ProductResponseJSON.class),
            @ApiResponse(code = 409, message = "Conflict",
                response = ConflictException.class),
            @ApiResponse(code = 500, message = "Internal server error",
                response = InternalServerErrorException.class)
        })
    @PostMapping
    public ResponseEntity<ProductResponseJSON> create(
        @RequestBody ProductRequestJSON requestJSON) {
        ProductResponseJSON response = this.create
            .execute(converter.convertRequestToDomain(requestJSON));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Product")
    @ApiResponses(
        value = {
            @ApiResponse(code = 204, message = "Updated"),
            @ApiResponse(code = 500, message = "Internal server error",
                response = InternalServerErrorException.class)
        })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(
        @PathVariable Integer id, @RequestBody ProductRequestJSON requestJSON) {
        this.update.execute(id, converter.convertRequestToDomain(requestJSON));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get a Product", response = ProductResponseJSON.class)
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Success",
                response = ProductResponseJSON.class),
            @ApiResponse(code = 500, message = "Internal server error",
                response = InternalServerErrorException.class)
        })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseJSON> get(@PathVariable Integer id) {
        ProductResponseJSON response = this.get.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Product")
    @ApiResponses(
        value = {
            @ApiResponse(code = 204, message = "Deleted"),
            @ApiResponse(code = 500, message = "Internal server error",
                response = InternalServerErrorException.class)
        })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.delete.execute(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
