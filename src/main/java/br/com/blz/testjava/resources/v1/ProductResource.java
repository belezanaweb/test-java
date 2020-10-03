package br.com.blz.testjava.resources.v1;

import br.com.blz.testjava.business.ProductBusiness;
import br.com.blz.testjava.domain.Product;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/products")
public class ProductResource {

    @Autowired
    private ProductBusiness productBusiness;

    @ApiOperation(value = "Api resposável por criar um novo produto", nickname = "Criacao de produto")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Retorno de sucesso na criacao de um novo produto"),
        @ApiResponse(code = 422, message = "Retorno de erro de regras de negocio"),
        @ApiResponse(code = 500, message = "Retorno de erro interno da aplicação")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(this.productBusiness.save(product), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Api resposável por buscar um produto por sku", nickname = "Busca de produto")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Retorno de sucesso na busca de um produto"),
        @ApiResponse(code = 422, message = "Retorno de erro de regras de negocio"),
        @ApiResponse(code = 500, message = "Retorno de erro interno da aplicação")
    })
    @GetMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> findBySku(@PathVariable(value = "sku") Long sku) {
        return new ResponseEntity<>(this.productBusiness.findBySku(sku), HttpStatus.OK);
    }

}
