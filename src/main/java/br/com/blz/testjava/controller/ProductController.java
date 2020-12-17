package br.com.blz.testjava.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Products")
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Get Product")
    @RequestMapping(value = "/{sku}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable final Long sku) {
        return ResponseEntity.ok(productService.getBySku(sku));
    }

    @ApiOperation(value = "Save Product")
    @RequestMapping(value = StringUtils.EMPTY, consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody @Validated final ProductDTO productDto)
            throws BusinessException {
        return new ResponseEntity<ProductDTO>(productService.save(productDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Product")
    @RequestMapping(value = "/{sku}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable final Long sku,
            @RequestBody @Validated final ProductDTO productDto) throws BusinessException {
        return new ResponseEntity<ProductDTO>(productService.update(productDto, sku), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Product")
    @RequestMapping(value = "/{sku}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProduct(@PathVariable final Long sku) throws BusinessException {
        productService.delete(sku); 
    }

}
