package br.com.blz.testjava.service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.Inventory;
import br.com.blz.testjava.dto.Produto;
import br.com.blz.testjava.dto.WareHouse;
import br.com.blz.testjava.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProdutoService {
	

	 
    private ProdutoRepository repository = new ProdutoRepository();
 
    public boolean create(Produto produto) {
    	if(!exists(produto.getSku())){
    		repository.createProduto(produto.getSku(), produto);
    	}else {
    		throw new IllegalArgumentException("Dois produtos são considerados iguais se os seus skus forem iguais");
    	}
    	return true;
    }
 
    public boolean delete(String sku) {
    	repository.deleteProduto(sku);
    	return true;
    }
 
    public Produto findBySku(String sku) {
   
    	Produto produto = repository.getProduto(sku);
    	if(produto != null) {
	    	Inventory inventory = produto.getInventory();
	    	Integer quantity =	inventory.getWarehouses().stream().map(p -> p.getQuantity()).reduce(0, Integer::sum);
	    	inventory.setQuantity(quantity);
	    	produto.setIsMarketable(inventory.getQuantity() > 0);
    	}
       	return produto;
     }


	public boolean update(Produto produto) {
	  	if(exists(produto.getSku())){
	  		repository.updateProduto(produto.getSku(), produto);
	  	}else {
	  		throw new IllegalArgumentException("A requisição deve receber o sku e atualizar com o produto que tbm esta vindo na requisição");
	  	}
    	return true;
    }
	
	public boolean exists(String sku) {
    	if (repository.getProduto(sku) != null) {
    		return true;
    	};
    	return false;
    }
 
 }
