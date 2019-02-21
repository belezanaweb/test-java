package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProdutoResource {


    @Autowired
    private ResourceImpl resourceImpl ;


    @PostMapping("/salvar-produto")
    public ResponseEntity<?> salvarProduto(@RequestBody @Valid Produto produto){
            return new ResponseEntity<>(resourceImpl.salvar(produto), HttpStatus.OK);
    }

    @PutMapping("/editar-produto")
    public ResponseEntity<?> editar(@RequestBody @Valid Produto produto){
        return new ResponseEntity<>(resourceImpl.editar(produto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{sku}")
    public ResponseEntity<?> deletar(@PathVariable(value = "sku") Long sku){
        return new ResponseEntity<>(resourceImpl.deletar(sku), HttpStatus.OK);
    }

    @PatchMapping(path = "/recuperar-Produto/{sku}")
    public ResponseEntity<?> recuperar(@PathVariable(value = "sku") Long sku){
       return new ResponseEntity<>(resourceImpl.recuperar(sku), HttpStatus.OK);
    }
}
