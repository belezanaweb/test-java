package br.com.blz.testjava.api.controllers;

import br.com.blz.testjava.api.dtos.ProblemDTO;
import br.com.blz.testjava.api.dtos.ProductDTO;
import br.com.blz.testjava.api.vos.ProductVO;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Api(tags = "Produtos", description = "Gerencia os produtos do seu catalogo.")
public class ProductController {

    private final ProductService service;

    @GetMapping("{sku}")
    @ApiOperation("Pesquisar")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Produto encontrado."),
    })
    public ResponseEntity<ProductDTO> findBySku(@PathVariable Long sku){
         return ResponseEntity.ok(new ProductDTO(service.findBySku(sku)));
    }

    @PostMapping
    @ApiOperation("Inserir")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Produto cadastrado."),
    })
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductVO vo, UriComponentsBuilder uriBuilder){
        Product product = service.save(vo);
        URI uri = uriBuilder.path("/products/{sku}").buildAndExpand(product.getSku()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product));
    }

    @DeleteMapping("{sku}")
    @ApiOperation("Excluir")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Produto excluída."),
        @ApiResponse(code = 404, message = "Cadastro não encontrado no sistema.", response = ProblemDTO.class)
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable Long sku){
        service.delete(sku);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{sku}")
    @ApiOperation("Alterar")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Produto alterado."),
        @ApiResponse(code = 404, message = "Cadastro não encontrado no sistema.", response = ProblemDTO.class)
    })
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductVO vo, @PathVariable Long sku){
        return ResponseEntity.ok(new ProductDTO(service.update(vo, sku)));
    }

}
