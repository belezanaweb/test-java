package br.com.blz.testjava.controller;


import br.com.blz.testjava.dtos.ProductDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@Slf4j
public class SkuController {

    @ApiOperation(value = "Create product")
    @PostMapping(value = "/v1/products")
    @ResponseStatus(CREATED)
    public void createProduct(@RequestBody @Valid ProductDTO productDTO) {

        System.out.println(productDTO);
    }


}
