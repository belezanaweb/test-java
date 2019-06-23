package br.com.blz.testjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping(value = "/produtos/{sku}")
    public ResponseEntity getProdutosBySku(@PathVariable Long sku) {
        Optional<Produto> byId = repository.findById(sku);
        if (byId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(byId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("prodouto (sku="+sku+") não encontrado");
    }
}
