/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zupper
 */
@Component
public class ProdutoRepository {
    
    List<Produto> produtos = new ArrayList<>();
     
    public List<Produto> listar(){
       return produtos;
    }
    
    public Produto buscarItem(Integer sku){
        
        Produto produto = produtos.stream().filter(p -> sku == p.getSku()).findAny().orElse(null);
        Integer qtdTotal = produto.getInventory().getWarehouses().stream().map(Warehouse::getQuantity).collect(Collectors.summingInt(Integer::intValue));
        produto.getInventory().setQuantity(qtdTotal);
        
        return produto;
    }
    
    public void inserir(Produto produto){
         produtos.add(produto);
    }
    
    public void remover(Integer sku){
         produtos.remove(produtos.stream().filter(p -> sku == p.getSku()).findAny().get());
    }
    
    public void atualizar(Integer sku, Produto produto){
        int indice = produtos.indexOf(produtos.stream().filter(p -> sku == p.getSku()).findAny().get());
        produto.setSku(sku);
        produtos.set(indice, produto);  
    }
}
