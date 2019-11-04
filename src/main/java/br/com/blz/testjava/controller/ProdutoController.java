package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.service.ProdutoService;
import br.com.blz.testjava.util.Util;
import br.com.blz.testjava.validation.OnUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/v1/produtos")
@Validated
public class ProdutoController {

    private ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping(Util.SKU)
    public ResponseEntity<Produto> getOne(@PathVariable Integer sku) {
        Produto product = this.service.obterByChave(sku);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Produto> save(@Valid @RequestBody Produto product) {
        this.service.salvar(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(Util.SKU)
    public ResponseEntity<Produto> update(@PathVariable Integer sku, @Validated(OnUpdate.class) @RequestBody Produto product) {
        product.setSku(sku);
        this.service.atualizar(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(Util.SKU)
    public ResponseEntity delete(@PathVariable Integer sku) {
        this.service.delete(sku);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}