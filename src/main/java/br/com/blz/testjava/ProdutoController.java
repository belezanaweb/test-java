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


    @PutMapping(value = "/{sku}")
    public ResponseEntity atualizarProduto(@PathVariable Long sku,@RequestBody Produto produto) {
        Optional<Produto> byId = repository.findById(sku);
        if (byId.isPresent()) {
            produto.setSku(sku);
            repository.save(produto);
            return ResponseEntity.status(HttpStatus.OK).body(produto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("produto (sku="+sku+") não encontrado");
    }

    @PostMapping
    public ResponseEntity criarProduto(@RequestBody Produto produto) {
        if (repository.existsById(produto.getSku())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("produto (sku="+produto.getSku()+") já existe na base");
        }
        Produto produtoSalvo = repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping(value = "/{sku}")
    public ResponseEntity getProdutoBySku(@PathVariable Long sku) {
        Optional<Produto> byId = repository.findById(sku);
        if (byId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(byId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
             .body("produto (sku="+sku+") não encontrado");
    }
}
