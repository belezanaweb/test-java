package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.util.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @GetMapping("/{sku}")
    public ResponseEntity get(@PathVariable int sku){

        Produto produto = Data.get(sku);

        if (Objects.isNull(produto)){
            return new ResponseEntity("Produto não encontrado", HttpStatus.OK);
        } else {
            return new ResponseEntity(produto, HttpStatus.OK);
        }


    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Produto produto) {

        if(!Objects.isNull(Data.get(produto.getSku()))){
            return new ResponseEntity("Produto já cadastrado!",HttpStatus.BAD_REQUEST);
        } else {
            Data.saveNewProduto(produto);
            return new ResponseEntity("Created " + produto.getSku(), HttpStatus.CREATED);
        }

    }

    @PutMapping("/update/{sku}")
    public ResponseEntity update(@PathVariable int sku, @RequestBody @Valid Produto produto) {

        if(produto.getSku() == sku){
            Data.saveNewProduto(produto);
            return new ResponseEntity("Saved " + produto.getSku(),HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Produto não confere o SKU",HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{sku}")
    public ResponseEntity delete(@PathVariable int sku){

        Data.delete(sku);

        return new ResponseEntity("Deletado " + sku, HttpStatus.OK);

    }

}
