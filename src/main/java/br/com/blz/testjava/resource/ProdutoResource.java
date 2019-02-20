package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
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

        return ResponseEntity.ok(resourceImpl.salvar(produto));
    }

    @PutMapping("/editar-produto")
    public ResponseEntity<?> editar(@RequestBody @Valid Produto produto){
        return ResponseEntity.ok(resourceImpl.editar(produto));
    }

    @GetMapping("/deletar-produto/{sku}")
    public void deletar(@PathVariable(value = "sku") Long sku){
         resourceImpl.deletar(sku);
    }

    @PatchMapping("/recuperar-Produto")
    public ResponseEntity<?> recuperar(@RequestBody @Valid Long skul){
       return ResponseEntity.ok(resourceImpl.recuperar(skul));

    }
}
