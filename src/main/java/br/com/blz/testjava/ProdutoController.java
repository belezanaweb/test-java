package br.com.blz.testjava;

import br.com.blz.testjava.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;


    @PostMapping
    public ResponseEntity criarProduto(@RequestBody Produto produto) {
        try {
            Produto produtoSalvo = repository.save(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ocorreu algum erro ao tentar criar o produto - [Produto="+produto+"] . " + e.getMessage());
        }
    }

    @GetMapping(value = "/{sku}")
    public ResponseEntity getProdutosBySku(@PathVariable Long sku) {
        Optional<Produto> byId = repository.findById(sku);
        if (byId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(byId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
             .body("prodouto (sku="+sku+") não encontrado");
    }
}
