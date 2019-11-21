/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zupper
 */
@Component
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<Produto> listar(){
       return produtoRepository.listar();
    }
    
    public Produto buscarItem(Integer sku){
        return produtoRepository.buscarItem(sku);
    }
    
    public void inserir(Produto produto){
        produtoRepository.inserir(produto);
    }
   
    public void remover(Integer sku){
        produtoRepository.remover(sku);
    }
    
    public void atualizar(Integer sku, Produto produto){
        produtoRepository.atualizar(sku, produto);
    }
}
