package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProdutoResource {


    @Autowired
    private ResourceImpl resourceImpl ;


    @PostMapping("/salvar-produto")
    public Produto salvarProduto(@RequestBody @Valid Produto produto){
        return  resourceImpl.salvar(produto);
    }

    @PutMapping("/editar-produto")
    public Produto editar(@RequestBody @Valid Produto produto){
        return resourceImpl.editar(produto);
    }

    @DeleteMapping("/deletar-produto")
    public void deletar(@RequestBody @Valid Produto produto){
         resourceImpl.deletar(produto);
    }
}
